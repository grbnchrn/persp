package com.emp.persp.dao;

import com.emp.persp.model.Employee;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Log LOGGER = LogFactory.getLog(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmpById(Long id){
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()){
            Employee emp = byId.get();
            LOGGER.info("Emp from DB " + emp.toString());
            return emp;
        }


        return new Employee();
    }

    public void updateEmpList(List<Employee> empList){
        empList.stream().forEach(employeeRepository::save);
    }

    public void updateEmp(Employee emp){
        Employee employee = employeeRepository.save(emp);
        LOGGER.info("Emp details updated in DB " + emp.toString());

    }
}
