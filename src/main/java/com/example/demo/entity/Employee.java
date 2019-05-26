package com.example.demo.entity;


//@Table("tblEmployees")
public class Employee {
//    @Id
    private Long id;
    private String name;
    private Boolean active;
    private Department department;

    public Employee(){}

    public Employee(Long empID, String empName, Boolean empActive, Department dep) {
        this.id = empID;
        this.name = empName;
        this.active = empActive;
        this.department = dep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format("Employee {id=%s, name=%s, active=%s, depart=%s}", id, name, active, department);
    }
}
