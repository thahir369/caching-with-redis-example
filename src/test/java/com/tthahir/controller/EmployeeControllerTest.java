package com.tthahir.controller;

import com.tthahir.dto.EmployeeDto;
import com.tthahir.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private EmployeeService employeeService;

  @Test
  void getHomePage_returnsWelcomeString() throws Exception {
    mockMvc.perform(get("/home"))
        .andExpect(status().isOk())
        .andExpect(content().string("Welcome to Employee home page!!"));

    verifyNoInteractions(employeeService);
  }

  @Test
  void getAllEmployees_returnsList() throws Exception {
    EmployeeDto e1 = EmployeeDto.builder().empId(1).name("Thahir").department("IT").salary(1000).build();
    EmployeeDto e2 = EmployeeDto.builder().empId(2).name("Sai").department("HR").salary(2000).build();

    when(employeeService.getAllEmployees()).thenReturn(List.of(e1, e2));

    mockMvc.perform(get("/getAllEmployees").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].empId").value(1))
        .andExpect(jsonPath("$[0].name").value("Thahir"))
        .andExpect(jsonPath("$[1].empId").value(2))
        .andExpect(jsonPath("$[1].name").value("Sai"));

    verify(employeeService, times(1)).getAllEmployees();
    verifyNoMoreInteractions(employeeService);
  }

  @Test
  void getEmployeeById_returnsEmployee() throws Exception {
    EmployeeDto e1 = EmployeeDto.builder().empId(1).name("Thahir").department("IT").salary(1000).build();
    when(employeeService.getEmployeeById(1)).thenReturn(e1);

    mockMvc.perform(get("/getAllEmployees/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.empId").value(1))
        .andExpect(jsonPath("$.name").value("Thahir"))
        .andExpect(jsonPath("$.department").value("IT"));

    verify(employeeService, times(1)).getEmployeeById(1);
    verifyNoMoreInteractions(employeeService);
  }

}
