package com.saber.employee_server.mapper;

import com.saber.employee_server.dto.EmployeeDto;
import com.saber.employee_server.model.Employee;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {

    Employee requestDtoToModel(EmployeeDto employeeDto);
}
