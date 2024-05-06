package com.saber.employee_server.services.impl;

import com.saber.common.dto.Constants;
import com.saber.employee_server.dto.EmployeeDeleteResponse;
import com.saber.employee_server.dto.EmployeeDto;
import com.saber.employee_server.mapper.EmployeeMapper;
import com.saber.employee_server.model.Employee;
import com.saber.employee_server.repositories.EmployeeRepository;
import com.saber.employee_server.services.EmployeeService;
import com.saber.common.dto.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final MessageSource messageSource;

    @Override
    public Employee save(EmployeeDto employeeDto) {
        Integer personalCode = employeeDto.getPersonalCode();
        String nationalCode = employeeDto.getNationalCode();
        if (employeeRepository.existByPersonalCodeAndNationalCode(nationalCode, personalCode)) {
            throw new BusinessException(getMessageBundle("employee.resource.duplicate"
                    , personalCode, nationalCode));
        }
        Employee employee = employeeMapper.requestDtoToModel(employeeDto);
        employee.setStatusCode(Constants.StatusCode.ACTIVE.getStatusCode());
        employee.setStatusTitle(Constants.StatusCode.ACTIVE.getStatusTitle());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee update(Integer id, EmployeeDto employeeDto) {
        if (!employeeRepository.existById(id)) {
            throw new BusinessException(getMessageBundle("employee.resource.notfound", id));
        }
        Integer personalCode = employeeDto.getPersonalCode();
        String nationalCode = employeeDto.getNationalCode();
        List<Employee> employees = employeeRepository.findAllByPersonalCodeAndNationalCode(nationalCode, personalCode);
        employees = employees.stream().filter(e -> !e.getId().equals(id)).toList();
        if (!employees.isEmpty()) {
            throw new BusinessException(getMessageBundle("employee.resource.duplicate"
                    , personalCode, nationalCode));
        }
        Employee employee = employeeMapper.requestDtoToModel(employeeDto);
        employee.setId(id);
        employee.setStatusCode(Constants.StatusCode.ACTIVE.getStatusCode());
        employee.setStatusTitle(Constants.StatusCode.ACTIVE.getStatusTitle());
        employeeRepository.update(employee);
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByNationalCodeAndPersonalCode(String nationalCode, Integer personalCode) {
        Employee employee = employeeRepository.findByPersonalCodeAndNationalCode(nationalCode, personalCode);
        if (employee == null) {
            throw new BusinessException(getMessageBundle("employee.resource.notfound"
                    , personalCode, nationalCode));
        }
        return employee;
    }

    @Override
    public Employee getById(Integer id) {
        log.info("Request get employee by id {}",id);
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new BusinessException(getMessageBundle("employee.resource.notfound", id));
        }
        log.info("Response employee by id {} ===> {}",id,employee);
        return employee;
    }

    @Override
    public EmployeeDeleteResponse deleteById(Integer id) {
        if (!employeeRepository.existById(id)) {
            throw new BusinessException(getMessageBundle("employee.resource.notfound", id));
        }
        employeeRepository.deleteById(id);
        EmployeeDeleteResponse deleteResponse = new EmployeeDeleteResponse();
        deleteResponse.setCode(0);
        deleteResponse.setText("employee delete successfully");
        return deleteResponse;
    }

    private String getMessageBundle(String message, Object... params) {
        return messageSource.getMessage(message, params, new Locale("fa"));
    }
}