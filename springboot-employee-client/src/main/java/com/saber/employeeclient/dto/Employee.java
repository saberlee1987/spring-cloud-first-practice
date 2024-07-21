package com.saber.employeeclient.dto;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Integer personalCode;
    private Integer jobCode;
    private String jobTitle;
    private String statusTitle;
    private Byte statusCode;
}
