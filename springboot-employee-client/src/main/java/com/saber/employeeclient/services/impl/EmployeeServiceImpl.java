package com.saber.employeeclient.services.impl;

import com.saber.common.dto.BusinessException;
import com.saber.employeeclient.dto.Employee;
import com.saber.employeeclient.dto.EmployeeDeleteResponse;
import com.saber.employeeclient.dto.EmployeeDto;
import com.saber.employeeclient.dto.EmployeeResponse;
import com.saber.employeeclient.services.EmployeeService;
import com.saber.employeeclient.services.ExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private final ExternalService externalService;

    @Value("${service.employee.name}")
    private String employeeName;

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {

        String url = "http://".concat(employeeName).concat("/services/employee");
        HttpEntity<EmployeeDto> entity = new HttpEntity<>(employeeDto);
        ResponseEntity<Employee> responseEntity = externalService.callingService(
                url, HttpMethod.POST, entity,
                "an error occurred when call service employee"
                , new ParameterizedTypeReference<>() {
                });
        if (responseEntity == null || responseEntity.getBody() == null) {
            throw new BusinessException("service employee return null response");
        }
        return responseEntity.getBody();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        String url = "http://".concat(employeeName).concat("/services/employee/").concat(String.valueOf(id));
        ResponseEntity<Employee> responseEntity = externalService.callingService(
                url, HttpMethod.GET, null,
                "an error occurred when call service employee"
                , new ParameterizedTypeReference<>() {
                });
        if (responseEntity == null || responseEntity.getBody() == null) {
            throw new BusinessException("service employee return null response");
        }
        return responseEntity.getBody();
    }

    @Override
    public EmployeeResponse getAllEmployees() {
        String url = "http://".concat(employeeName).concat("/services/employee");
        ResponseEntity<EmployeeResponse> responseEntity = externalService.callingService(
                url, HttpMethod.GET, null,
                "an error occurred when call service employee"
                , new ParameterizedTypeReference<>() {
                });
        if (responseEntity == null || responseEntity.getBody() == null) {
            throw new BusinessException("service employee return null response");
        }
        return responseEntity.getBody();
    }

    @Override
    public Employee getEmployeeByNationalCodeAndPersonalCode(String nationalCode, Integer personalCode) {
        String url = "http://".concat(employeeName).concat("/services/employee/getByNationalCodeAndPersonalCode");
        url = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("nationalCode", nationalCode)
                .queryParam("personalCode", personalCode)
                .toUriString();
        ResponseEntity<Employee> responseEntity = externalService.callingService(
                url, HttpMethod.GET, null,
                "an error occurred when call service employee"
                , new ParameterizedTypeReference<>() {
                });
        if (responseEntity == null || responseEntity.getBody() == null) {
            throw new BusinessException("service employee return null response");
        }
        return responseEntity.getBody();
    }

    @Override
    public Employee update(Integer id, EmployeeDto employeeDto) {

        String url = "http://".concat(employeeName).concat("/services/employee/").concat(String.valueOf(id));
        HttpEntity<EmployeeDto> entity = new HttpEntity<>(employeeDto);
        ResponseEntity<Employee> responseEntity = externalService.callingService(
                url, HttpMethod.PUT, entity,
                "an error occurred when call service employee"
                , new ParameterizedTypeReference<>() {
                });
        if (responseEntity == null || responseEntity.getBody() == null) {
            throw new BusinessException("service employee return null response");
        }
        return responseEntity.getBody();
    }

    @Override
    public EmployeeDeleteResponse deleteEmployeeById(Integer id) {
        String url = "http://".concat(employeeName).concat("/services/employee/").concat(String.valueOf(id));
        ResponseEntity<EmployeeDeleteResponse> responseEntity = externalService.callingService(
                url, HttpMethod.DELETE, null,
                "an error occurred when call service employee"
                , new ParameterizedTypeReference<>() {
                });
        if (responseEntity == null || responseEntity.getBody() == null) {
            throw new BusinessException("service employee return null response");
        }
        return responseEntity.getBody();
    }
}
