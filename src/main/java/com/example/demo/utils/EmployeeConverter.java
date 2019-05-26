package com.example.demo.utils;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeConverter {

    public static EmployeeDTO employeeToDTO(Employee emp){
        return new EmployeeDTO(
                emp.getId(),
                emp.getName(),
                emp.getActive(),
                emp.getDepartment().getName(),
                emp.getDepartment().getId()
        );
    }

    public static Employee dtoToEmployee(EmployeeDTO dto){
        return new Employee(
                dto.getId(),
                dto.getName(),
                dto.getActive(),
                new Department(dto.getDpId(), dto.getDepartment())
        );
    }

    public static List<EmployeeDTO> employeesToDTOList(List<Employee> list){
        List<EmployeeDTO> beans = new ArrayList<>(list.size());
        for(Employee emp : list){
            beans.add( new EmployeeDTO(
                    emp.getId(),
                    emp.getName(),
                    emp.getActive(),
                    emp.getDepartment().getName(),
                    emp.getDepartment().getId()
            ));
        }
        return beans;
    }

    public static Page<EmployeeDTO> pageToDtoPage(Page<Employee> page){
        Page<EmployeeDTO> pageDto = new Page<>();
        pageDto.setPageItems(page.getPageItems().stream()
                .map(EmployeeConverter::employeeToDTO)
                .collect(Collectors.toList()));
        pageDto.setPageNumber(page.getPageNumber());
        pageDto.setPagesAvailable(page.getPagesAvailable());
        pageDto.setTotalItems(page.getTotalItems());
        return pageDto;
    }
}
