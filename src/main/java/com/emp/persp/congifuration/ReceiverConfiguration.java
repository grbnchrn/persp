package com.emp.persp.congifuration;

import com.emp.persp.dao.EmployeeService;
import com.emp.persp.model.Employee;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
public class ReceiverConfiguration {

    @Autowired
    private EmployeeService empService;

    private static final Log LOGGER = LogFactory.getLog(ReceiverConfiguration.class);

    private static final String SUBSCRIPTION = "persp-topic-inbound-subscription";

    @Bean
    public MessageChannel pubsubInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("pubsubInputChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, SUBSCRIPTION);
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);
        adapter.setPayloadType(String.class);
        return adapter;
    }

    @ServiceActivator(inputChannel = "pubsubInputChannel")
    public void messageReceiver(String payload,
                                @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
        LOGGER.info("Message arrived! Payload: " + payload);
        try{
            if(!payload.isEmpty() && !payload.startsWith("Emp Id")) {
                Employee emp = new Employee();
                String[] empArray = payload.split(",");
                emp.setEmpId(Long.parseLong(empArray[0]));
                emp.setFirstName(empArray[1]);
                emp.setLastName(empArray[2]);
                emp.setGender(empArray[3]);
                emp.setEmail(empArray[4]);
                emp.setDateOfBirth(empArray[5]);
                emp.setAge(Float.parseFloat(empArray[6]));
                emp.setWeight(Float.parseFloat(empArray[7]));
                emp.setDateOfJoining(empArray[8]);
                emp.setExperience(Float.parseFloat(empArray[9]));
                emp.setSalary(Double.parseDouble(empArray[10]));
                emp.setContactNumber(empArray[11]);
                emp.setCountry(empArray[12]);
                emp.setCity(empArray[13]);
                emp.setState(empArray[14]);
                emp.setZip(empArray[15]);
                emp.setRegion(empArray[16]);

                empService.updateEmp(emp);

             }
        }catch (Exception exp){
            LOGGER.info("Exception : \n" );
            exp.printStackTrace();
        }

        message.ack();

    }

}
