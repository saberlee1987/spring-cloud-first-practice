package com.saber.employeeclient.services;

import com.saber.employeeclient.dto.Employee;
import com.saber.employeeclient.dto.EmployeeDeleteResponse;
import com.saber.employeeclient.dto.EmployeeDto;
import com.saber.employeeclient.dto.EmployeeResponse;

public interface EmployeeService {
    Employee saveEmployee(EmployeeDto employeeDto);
    Employee getEmployeeById(Integer id);
    EmployeeResponse getAllEmployees();

    Employee getEmployeeByNationalCodeAndPersonalCode(String nationalCode, Integer personalCode);

    Employee update(Integer id, EmployeeDto employeeDto);

    EmployeeDeleteResponse deleteEmployeeById(Integer id);
}
