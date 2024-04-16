package com.saber.employee_server.repositories;

import com.saber.employee_server.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee save(Employee employee);

    void update(Employee employee);

    void deleteById(Integer id);

    List<Employee> findAll();

    Employee findById(Integer id);

    Employee findByPersonalCode(Integer personalCode);

    Employee findByPersonalCodeAndNationalCode(String nationalCode, Integer personalCode);
    List<Employee> findAllByPersonalCodeAndNationalCode(String nationalCode, Integer personalCode);
    Boolean existByPersonalCodeAndNationalCode(String nationalCode, Integer personalCode);
    Boolean existById(Integer id);
}
