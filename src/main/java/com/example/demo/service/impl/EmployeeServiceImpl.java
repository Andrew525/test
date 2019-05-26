package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.repository.Impl.EmployeeJdbcRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.utils.EmployeeConverter;
import com.example.demo.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeJdbcRepository empRepo;

    @Override
    public EmployeeDTO getEmployeeById(Long empId) {
        return EmployeeConverter.employeeToDTO(empRepo.findById(empId));
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO empDto) {
        return EmployeeConverter.employeeToDTO(empRepo.save(EmployeeConverter.dtoToEmployee(empDto)));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return empRepo.findAll().stream()
                .map(EmployeeConverter::employeeToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EmployeeDTO> getPageEmployees(Integer pageNo, Integer size) {
        return EmployeeConverter.pageToDtoPage(empRepo.getPageEmployees(pageNo, size));
    }

    @Override
    public void deleteEmployee(EmployeeDTO empDto) {
        empRepo.deleteById(empDto.getId());
    }

    @Override
    public void deleteEmployeeById(Long empId) {
        empRepo.deleteById(empId);
    }

    @Override
    public Page<EmployeeDTO> searchByName(Integer pageNo, Integer size, String query){
        return EmployeeConverter.pageToDtoPage( empRepo.serchByName( pageNo, size, query ) );
    }
}
