package com.tthahir.utils;

import com.tthahir.entity.Employee;

public class EmployeeUtils {

  private EmployeeUtils() {
  }

  public static Employee convertDtoToEntity(com.tthahir.dto.EmployeeDto employeeDto) {
    return Employee.builder()
        .empId(employeeDto.getEmpId())
        .name(employeeDto.getName())
        .department(employeeDto.getDepartment())
        .salary(employeeDto.getSalary())
        .build();
  }

  public static com.tthahir.dto.EmployeeDto convertEntityToDto(Employee employee) {
    com.tthahir.dto.EmployeeDto employeeDto = new com.tthahir.dto.EmployeeDto();
    employeeDto.setEmpId(employee.getEmpId());
    employeeDto.setName(employee.getName());
    employeeDto.setDepartment(employee.getDepartment());
    employeeDto.setSalary(employee.getSalary());
    return employeeDto;
  }
}
