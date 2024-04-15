package com.saber.employee_server.services;

import com.saber.employee_server.dto.EmployeeDto;
import com.saber.employee_server.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee save(EmployeeDto employeeDto);
    Employee update(Integer id ,EmployeeDto employeeDto);
    List<Employee> getAll();
    Employee getEmployeeByNationalCodeAndPersonalCode(String nationalCode,Integer personalCode);
    Employee getById(Integer id);
    void deleteById(Integer id);
}
