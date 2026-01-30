package com.tthahir.boostrap;

import com.tthahir.entity.Employee;
import com.tthahir.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

  private final EmployeeRepository employeeRepository;

  @Override
  public void run(String... args) throws Exception {

    employeeRepository.save(Employee.builder().name("Thahir").department("IT").build());
    employeeRepository.save(Employee.builder().name("Sai").department("HR").build());
    employeeRepository.save(Employee.builder().name("Vamsi").department("IT").build());


  }
}
