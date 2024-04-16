package com.saber.employee_server.repositories.impl;

import com.saber.employee_server.model.Employee;
import com.saber.employee_server.repositories.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private volatile Integer id = 0;
    private final Map<Integer, Employee> employeeDataBase;

    public EmployeeRepositoryImpl() {
        this.employeeDataBase = new ConcurrentHashMap<>();
    }

    private synchronized Integer nextId() {
        return ++id;
    }

    @Override
    public Employee save(Employee employee) {
        employee.setId(nextId());
        employeeDataBase.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public void update(Employee employee) {
        employeeDataBase.put(employee.getId(), employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeDataBase.remove(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDataBase.values().stream().toList();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeDataBase.values().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public Employee findByPersonalCode(Integer personalCode) {
        return employeeDataBase.values().stream()
                .filter(e -> e.getPersonalCode().equals(personalCode))
                .findFirst().orElse(null);
    }

    @Override
    public Employee findByPersonalCodeAndNationalCode(String nationalCode, Integer personalCode) {
        return employeeDataBase.values().stream()
                .filter(e -> e.getPersonalCode().equals(personalCode)
                        && e.getNationalCode().equals(nationalCode))
                .findFirst().orElse(null);
    }

    @Override
    public List<Employee> findAllByPersonalCodeAndNationalCode(String nationalCode, Integer personalCode) {
        return employeeDataBase.values().stream()
                .filter(e -> e.getPersonalCode().equals(personalCode)
                        && e.getNationalCode().equals(nationalCode))
                .toList();
    }

    @Override
    public Boolean existByPersonalCodeAndNationalCode(String nationalCode, Integer personalCode) {
        return employeeDataBase.values().stream()
                .anyMatch(e -> e.getPersonalCode().equals(personalCode)
                        && e.getNationalCode().equals(nationalCode));
    }

    @Override
    public Boolean existById(Integer id) {
        return employeeDataBase.containsKey(id);
    }
}
