package com.tthahir.controller;


import com.tthahir.dto.EmployeeDto;
import com.tthahir.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {


  private final EmployeeService employeeService;

  @GetMapping("/home")
  public String getHomePage() {
    return "Welcome to Employee home page!!";
  }

  @GetMapping("/getAllEmployees")
  public List<EmployeeDto> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("/getAllEmployees/{id}")
  public EmployeeDto getEmployeeById(@PathVariable int id) {
    return employeeService.getEmployeeById(id);
  }


}


