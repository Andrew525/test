package com.example.demo.repository;

import com.example.demo.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository {

    Optional<Department> findById(Long id);
    List<Department> findAll();
    Department save(Department employee);
    boolean create(Department employee);
    boolean update(Department employee);
    boolean delete(Department employee);
    void deleteById(Long id);
}
