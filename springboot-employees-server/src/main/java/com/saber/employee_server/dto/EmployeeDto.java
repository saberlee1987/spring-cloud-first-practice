package com.saber.employee_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDto {
    @NotBlank(message = "{employee.firstName.required}")
    private String firstName;
    @NotBlank(message = "{employee.lastName.required}")
    private String lastName;
    @NotBlank(message = "{employee.nationalCode.required}")
    private String nationalCode;
    @NotNull(message = "{employee.personalCode.required}")
    private Integer personalCode;
    @NotNull(message = "{employee.jobCode.required}")
    private Integer jobCode;
    @NotBlank(message = "{employee.jobTitle.required}")
    private String jobTitle;
}
