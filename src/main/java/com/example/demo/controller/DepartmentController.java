package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/departments")
@RestController
public class DepartmentController {

    @Autowired
    DepartmentServiceImpl dpService;


    @GetMapping
    public List<Department> getDapertments() {
        return dpService.getDepartments();
    }

    //TODO: add more methods
}
