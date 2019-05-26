package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import com.example.demo.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/employees")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService empService;

    @GetMapping
    public Page<EmployeeDTO> getPageEmployees(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNo,
                                              @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                              @RequestParam(value = "search", required = false, defaultValue = "") String query) {
        if(pageNo <= 0 || limit <= 0)
            throw new IllegalArgumentException();
        if(query.equals(""))
            return empService.getPageEmployees(pageNo, limit);
        else
            return empService.searchByName(pageNo, limit, query);

    }

    @ResponseBody
    @GetMapping("/{empID}")
    public EmployeeDTO getEmployeeById(@PathVariable Long empID) {
        return empService.getEmployeeById(empID);
//        .orElseThrow(() -> new NotFoundException("Employee", id));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO creatEmployee(@RequestBody EmployeeDTO empDto) {
        //Preconditions.checkNotNull(resource);
        return empService.saveEmployee(empDto);
    }

    @PutMapping("/{empID}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmployee(@PathVariable( "empID" ) Long empID,
                               @RequestBody EmployeeDTO empDto) {
        empService.saveEmployee(empDto);
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newEmployee.getName());
//                    employee.setActive(newEmployee.getActive());
//                    employee.setEmp_dpID(newEmployee.getEmp_dpID());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    return repository.save(newEmployee);
//                });
    }


    @DeleteMapping("/{empID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployeeById(@PathVariable Long empID) {
         empService.deleteEmployeeById(empID);
        // new ResponseEntity("No Employee found for ID " + id, HttpStatus.NOT_FOUND);
    }

}

