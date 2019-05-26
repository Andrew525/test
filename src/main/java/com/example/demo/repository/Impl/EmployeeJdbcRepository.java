package com.example.demo.repository.Impl;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.utils.Page;
import com.example.demo.utils.PaginationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@Transactional
@Repository
public class EmployeeJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getLong("empID"));
            employee.setName(rs.getString("empName"));
            employee.setActive(rs.getBoolean("empActive"));
            employee.setDepartment(
                    new Department(rs.getLong("dpID"), rs.getString("dpName")));
            return employee;
        }
    }

    public Optional<Employee> findById2(long id) {
        Employee emp = jdbcTemplate.queryForObject("select * from tblEmployees " +
                "LEFT OUTER JOIN tblDepartments ON emp_dpID = dpID where empID=?", new Object[]{id},
                new EmployeeJdbcRepository.EmployeeRowMapper());
        return Optional.ofNullable(emp);
    }
    public Employee findById(long id) {
        return jdbcTemplate.queryForObject("select * from tblEmployees " +
                        "LEFT OUTER JOIN tblDepartments ON emp_dpID = dpID where empID=?", new Object[]{id},
                new EmployeeJdbcRepository.EmployeeRowMapper());
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query(
                "SELECT empID, empName, empActive, dpID, dpName FROM tblEmployees " +
                        "LEFT OUTER JOIN tblDepartments ON emp_dpID = dpID",
                new EmployeeRowMapper());
    }


    public Employee save(Employee employee) {
        if(employee.getId() == null){
            create(employee);
        }else {
            update(employee);
        }
        return employee;
    }

    public boolean delete(Employee employee) {
        deleteById(employee.getId());
        return true;
    }

    public void deleteById(long id) {
        jdbcTemplate.update("delete from tblEmployees where empID=?", new Object[]{id});
    }

    public boolean create(Employee emp) {
        return jdbcTemplate.update(
                "insert into tblEmployees (empName, empActive, emp_dpID) " + "values(?, ?, ?)",
                new Object[]{emp.getName(), emp.getActive(), emp.getDepartment().getId()})
                >= 1;
    }

    public boolean update(Employee emp) {
        return jdbcTemplate.update(
                "update tblEmployees  set empName = ?, empActive = ?, emp_dpID = ? where empID = ?",
                new Object[]{emp.getName(), emp.getActive(), emp.getDepartment().getId(), emp.getId()})
                >= 1;
    }

    /**
     *
     * // Пагінація виконується на базі даних
     * // (можлива пагінація на сервері)
     */
    public Page<Employee> getPageEmployees(int pageNo, int pageSize){
        PaginationHelper<Employee> ph = new PaginationHelper<>();
        return ph.fetchPage(
                jdbcTemplate,
                "SELECT count(*) FROM tblEmployees",
                "SELECT empID, empName, empActive, dpID, dpName FROM tblEmployees LEFT OUTER JOIN tblDepartments ON emp_dpID = dpID ORDER BY empID",
                new Object[]{},
                pageNo,
                pageSize,
                new EmployeeRowMapper()
        );
    }


    public Page<Employee> serchByName(Integer pageNo, Integer pageSize, String query){
        PaginationHelper<Employee> ph = new PaginationHelper<>();
        return ph.fetchPage(
                jdbcTemplate,
                "SELECT count(*) FROM tblEmployees",
                "SELECT empID, empName, empActive, dpID, dpName FROM (SELECT empID, empName, empActive, emp_dpID FROM tblEmployees WHERE empName like ? ) emp LEFT OUTER JOIN tblDepartments ON emp_dpID = dpID ORDER BY empID",
                new String[]{query + "%"},
                pageNo,
                pageSize,
                new EmployeeRowMapper()
        );
    }

}
