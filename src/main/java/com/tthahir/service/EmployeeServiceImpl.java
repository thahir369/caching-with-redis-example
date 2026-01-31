package com.tthahir.service;

import com.tthahir.dto.EmployeeDto;
import com.tthahir.entity.Employee;
import com.tthahir.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


  private final EmployeeRepository employeeRepository;

  @Override
  @Cacheable(value = "employees", key = "'all'")
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
  @Cacheable(value = "employees", key = "#empId")
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
  @CacheEvict(value = "employees", key = "'all'")
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
  @CachePut(value = "employee", key = "#emp.id")
  @CacheEvict(value = "employees", key = "'all'")
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
  @Caching(evict = {
      @CacheEvict(value = "employee", key = "#id"),
      @CacheEvict(value = "employees", key = "'all'")
  })
  // 5. DELETE: Removes the specific ID from cache and clears the list cache
  public void deleteEmployee(int empId) {
    employeeRepository.deleteById(empId);
  }
}
