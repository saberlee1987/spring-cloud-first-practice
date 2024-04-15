package com.saber.employee_server.services.impl;

import com.saber.common.dto.Constants;
import com.saber.employee_server.dto.EmployeeDto;
import com.saber.employee_server.mapper.EmployeeMapper;
import com.saber.employee_server.model.Employee;
import com.saber.employee_server.repositories.EmployeeRepository;
import com.saber.employee_server.services.EmployeeService;
import com.saber.common.dto.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final MessageSource messageSource;

    @Override
    public Employee save(EmployeeDto employeeDto) {
        Integer personalCode = employeeDto.getPersonalCode();
        String nationalCode = employeeDto.getNationalCode();
        if (employeeRepository.existByPersonalCodeAndNationalCode(nationalCode, personalCode)) {
            throw new BusinessException(String.format("employee for personalCode %s , nationalCode %s already exist"
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
        Integer personalCode = employeeDto.getPersonalCode();
        String nationalCode = employeeDto.getNationalCode();
        if (!employeeRepository.existById(id)) {
            throw new BusinessException(String.format("employee for personalCode %s , nationalCode %s does not exist"
                    , personalCode, nationalCode));
        }
        Employee employee = employeeMapper.requestDtoToModel(employeeDto);
        employee.setId(id);
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
            throw new BusinessException(getMessageBundle("employee.resource.duplicate"
                    , personalCode, nationalCode));
        }
        return employee;
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new BusinessException(getMessageBundle("employee.resource.duplicate" , id));
        }
        return employee;
    }

    @Override
    public void deleteById(Integer id) {
        if (!employeeRepository.existById(id)){
            throw new BusinessException(getMessageBundle("employee.resource.duplicate" , id));
        }
        employeeRepository.deleteById(id);
    }
    private String getMessageBundle(String message, Object... params) {
        return messageSource.getMessage(message, params, new Locale("fa"));
    }
}