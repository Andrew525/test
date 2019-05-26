package com.example.demo.service.impl;

import com.example.demo.entity.Department;
import com.example.demo.repository.Impl.DepartmentJdbcRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentJdbcRepository dpRepo;

    @Override
    public List<Department> getDepartments(){
        return dpRepo.findAll();
    }

}
