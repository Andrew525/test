package com.example.demo.repository.Impl;

import com.example.demo.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentJdbcRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department dep = new Department();
            dep.setId(rs.getLong("dpID"));
            dep.setName(rs.getString("dpName"));
            return dep;
        }
    }

    public Department save(Department dep) {
        if(dep.getId() == null){
            create(dep);
        }else {
            update(dep);
        }
        return dep;
    }

    public Optional findById(long id) {
        Department emp = jdbcTemplate.queryForObject("select * from tblDepartments where dpID=?", new Object[]{id},
                new DepartmentJdbcRepository.DepartmentRowMapper());
        return Optional.ofNullable(emp);
    }

    public List<Department> findAll() {
        return jdbcTemplate.query(
                "SELECT dpID, dpName FROM tblDepartments ",
                new DepartmentJdbcRepository.DepartmentRowMapper());
    }

    public boolean create(Department dep) {
        return jdbcTemplate.update(
                "insert into tblDepartments ( dpName ) " + "values(?)",
                new Object[]{dep.getName()})
                >= 1;
    }

    public boolean update(Department dep) {
        return jdbcTemplate.update(
                "update tblDepartments set dpName = ? where dpID = ?",
                new Object[]{dep.getName(), dep.getId()})
                >= 1;
    }
}
