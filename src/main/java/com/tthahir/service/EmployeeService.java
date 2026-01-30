package com.tthahir.service;

import com.tthahir.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {


  List<EmployeeDto> getAllEmployees();

  EmployeeDto getEmployeeById(int empId);

  EmployeeDto createEmployee(EmployeeDto employeeDto);

  EmployeeDto updateEmployee(int empId, EmployeeDto employeeDto);

  void deleteEmployee(int empId);

}
