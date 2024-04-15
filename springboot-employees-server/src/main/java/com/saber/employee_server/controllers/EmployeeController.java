package com.saber.employee_server.controllers;

import com.saber.employee_server.dto.EmployeeDto;
import com.saber.employee_server.dto.EmployeeResponse;
import com.saber.employee_server.model.Employee;
import com.saber.employee_server.services.EmployeeService;
import com.saber.common.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "${service.baseUrl}"
        ,produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(name = "employee controller",description = "employee rest api")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(method = "POST",summary = "save employee",description = "save employee"
            ,operationId = "saveEmployee"
            ,requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true
            ,content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = EmployeeDto.class)
            ,examples = @ExampleObject(value = "{\n" +
            "  \"firstName\": \"saber\",\n" +
            "  \"lastName\": \"azizi\",\n" +
            "  \"nationalCode\": \"0079028748\",\n" +
            "  \"personalCode\": 1246,\n" +
            "  \"jobCode\": 16,\n" +
            "  \"jobTitle\": \"java programming\"\n" +
            "}")))
            ,responses = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "504", description = "GATEWAY_TIMEOUT",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
    })
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.save(employeeDto));
    }
    @GetMapping
    @Operation(method = "GET",summary = "get All employees",description = "get All employees"
            ,operationId = "getAllEmployees")
    public ResponseEntity<EmployeeResponse> getAllEmployees(){
        return ResponseEntity.ok(new EmployeeResponse(employeeService.getAll()));
    }


}
