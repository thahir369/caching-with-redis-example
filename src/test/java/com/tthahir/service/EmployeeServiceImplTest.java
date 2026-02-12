package com.tthahir.service;

import com.tthahir.dto.EmployeeDto;
import com.tthahir.entity.Employee;
import com.tthahir.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Test
  void getAllEmployees() {
    Employee e1 = Employee.builder().empId(1).name("Thahir").department("IT").salary(1000).build();
    Employee e2 = Employee.builder().empId(2).name("Sai").department("HR").salary(2000).build();

    when(employeeRepository.findAll()).thenReturn(List.of(e1, e2));

    List<EmployeeDto> result = employeeService.getAllEmployees();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(1, result.get(0).getEmpId());
    assertEquals("Thahir", result.get(0).getName());
    assertEquals(2, result.get(1).getEmpId());
    assertEquals("Sai", result.get(1).getName());

    verify(employeeRepository, times(1)).findAll();
    verifyNoMoreInteractions(employeeRepository);
  }

  @Test
  void getEmployeeById_found() {
    Employee e = Employee.builder().empId(1).name("Thahir").department("IT").salary(1000).build();
    when(employeeRepository.findById(1)).thenReturn(Optional.of(e));

    EmployeeDto dto = employeeService.getEmployeeById(1);

    assertNotNull(dto);
    assertEquals(1, dto.getEmpId());
    assertEquals("Thahir", dto.getName());
    assertEquals("IT", dto.getDepartment());

    verify(employeeRepository).findById(1);
  }

  @Test
  void getEmployeeById_notFound() {
    when(employeeRepository.findById(999)).thenReturn(Optional.empty());

    RuntimeException ex = assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(999));
    assertEquals("Employee not found", ex.getMessage());

    verify(employeeRepository).findById(999);
  }

  @Test
  void createEmployee() {
    EmployeeDto input = EmployeeDto.builder().empId(0).name("New").department("Dev").salary(3000).build();
    Employee saved = Employee.builder().empId(10).name("New").department("Dev").salary(3000).build();

    when(employeeRepository.save(any(Employee.class))).thenReturn(saved);

    EmployeeDto result = employeeService.createEmployee(input);

    assertNotNull(result);
    assertEquals(10, result.getEmpId());
    assertEquals("New", result.getName());

    verify(employeeRepository).save(any(Employee.class));
  }

  @Test
  void updateEmployee() {
    int id = 1;
    Employee existing = Employee.builder().empId(id).name("Old").department("IT").salary(1000).build();
    Employee updatedEntity = Employee.builder().empId(id).name("Updated").department("IT").salary(1500).build();
    EmployeeDto updateDto = EmployeeDto.builder().empId(id).name("Updated").department("IT").salary(1500).build();

    when(employeeRepository.findById(id)).thenReturn(Optional.of(existing));
    when(employeeRepository.save(existing)).thenReturn(updatedEntity);

    EmployeeDto result = employeeService.updateEmployee(id, updateDto);

    assertNotNull(result);
    assertEquals(id, result.getEmpId());
    assertEquals("Updated", result.getName());
    assertEquals(1500, result.getSalary());

    verify(employeeRepository).findById(id);
    verify(employeeRepository).save(existing);
  }

  @Test
  void deleteEmployee() {
    int id = 5;

    doNothing().when(employeeRepository).deleteById(id);

    employeeService.deleteEmployee(id);

    verify(employeeRepository).deleteById(id);
  }
}
