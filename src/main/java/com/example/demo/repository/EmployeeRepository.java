package com.example.demo.repository;

import com.example.demo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> findById(Long id);
    List<Employee> findAll();
    Employee save(Employee employee);
    boolean create(Employee employee);
    boolean update(Employee employee);
    boolean delete(Employee employee);
    void deleteById(Long id);
    List<Employee> search(Integer pageNo, Integer size, String query);
}
