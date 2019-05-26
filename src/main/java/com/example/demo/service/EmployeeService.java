package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.utils.Page;

import java.util.List;

public interface EmployeeService {

        EmployeeDTO getEmployeeById(Long empId);
        EmployeeDTO saveEmployee(EmployeeDTO empDto);

        List<EmployeeDTO> getAllEmployees();
        Page<EmployeeDTO> getPageEmployees(Integer pageNo, Integer size);

        void deleteEmployee(EmployeeDTO empDto);
        void deleteEmployeeById(Long empId);

        Page<EmployeeDTO> searchByName(Integer pageNo, Integer size, String query);

}
