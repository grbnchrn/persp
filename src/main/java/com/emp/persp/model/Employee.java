package com.emp.persp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name="Emp_Id")
    private Long empId;

    @Column(name="First_Name")
    private String firstName;

    @Column(name="Last_Name")
    private String lastName;

    @Column(name="Email")
    private String email;

    @Column(name="Phone_Number")
    private String contactNumber;

    @Column(name="Gender")
    private String gender;

    @Column(name="Country")
    private String country;

    @Column(name="City")
    private String city;

    @Column(name="State")
    private String state;

    @Column(name="Zip")
    private String zip;

    @Column(name="Region")
    private String region;

    @Column(name="Date_of_Joining")
    private String dateOfJoining;

    @Column(name="Date_of_Birth")
    private String dateOfBirth;

    @Column(name="Age")
    private Float age;

    @Column(name="Weight")
    private Float weight;

    @Column(name="Experience")
    private Float experience;

    @Column(name="Salary")
    private Double salary;
}

