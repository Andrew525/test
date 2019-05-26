package com.example.demo;

import com.example.demo.repository.Impl.DepartmentJdbcRepository;
import com.example.demo.repository.Impl.EmployeeJdbcRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BootDemoApplicationTests {

	@Autowired
	private EmployeeJdbcRepository employeeRepository;
	@Autowired
	private DepartmentJdbcRepository departmentRepo;

	@Test
	public void contextLoads() {

////		System.out.println(departmentRepo.findAll());
//		System.out.println(departmentRepo.findById(3));
////		System.out.println(employeeRepository.findAll());
//		Employee em = employeeRepository.findById(3).get();
//		System.out.println (em);
//		em.setActive(false);
//		employeeRepository.save(em);
//		em = employeeRepository.findById(3).get();
//		System.out.println(em);

//			Page<Employee> page = employeeRepository.getPageEmployees(1, 5);
//			System.out.println(page.getPageItems());



	}

}
