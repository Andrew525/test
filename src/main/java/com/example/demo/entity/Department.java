package com.example.demo.entity;

//@Table("tblDepartments")
public class Department {

//    @Id
    private Long id;
    private String name;
    public Department(){}

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("Department [id=%s, name=%s]", id, name);
    }
}
