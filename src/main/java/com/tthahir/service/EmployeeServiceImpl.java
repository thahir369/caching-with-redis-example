package com.tthahir.service;

import com.tthahir.dto.EmployeeDto;
import com.tthahir.entity.Employee;
import com.tthahir.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


  private final EmployeeRepository employeeRepository;

  @Override
  public List<EmployeeDto> getAllEmployees() {
    List<Employee> employeeList = employeeRepository.findAll();
    return employeeList.stream().map(employee -> EmployeeDto.builder()
            .empId(employee.getEmpId())
            .name(employee.getName())
            .department(employee.getDepartment())
            .salary(employee.getSalary())
            .build())
        .toList();
  }

  @Override
  public EmployeeDto getEmployeeById(int empId) {
    Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found"));
    return EmployeeDto.builder()
        .empId(employee.getEmpId())
        .name(employee.getName())
        .department(employee.getDepartment())
        .salary(employee.getSalary())
        .build();
  }

  @Override
  public EmployeeDto createEmployee(EmployeeDto employeeDto) {
    Employee employee = Employee.builder()
        .empId(employeeDto.getEmpId())
        .name(employeeDto.getName())
        .department(employeeDto.getDepartment())
        .salary(employeeDto.getSalary())
        .build();
    Employee savedEmployee = employeeRepository.save(employee);
    return EmployeeDto.builder()
        .empId(savedEmployee.getEmpId())
        .name(savedEmployee.getName())
        .department(savedEmployee.getDepartment())
        .salary(savedEmployee.getSalary())
        .build();
  }

  @Override
  public EmployeeDto updateEmployee(int empId, EmployeeDto employeeDto) {
    Employee existingEmployee = employeeRepository.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found"));
    existingEmployee.setName(employeeDto.getName());
    existingEmployee.setDepartment(employeeDto.getDepartment());
    existingEmployee.setSalary(employeeDto.getSalary());
    Employee updatedEmployee = employeeRepository.save(existingEmployee);
    return EmployeeDto.builder()
        .empId(updatedEmployee.getEmpId())
        .name(updatedEmployee.getName())
        .department(updatedEmployee.getDepartment())
        .salary(updatedEmployee.getSalary())
        .build();
  }

  @Override
  public void deleteEmployee(int empId) {
    employeeRepository.deleteById(empId);
  }
}
