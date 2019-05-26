package com.example.demo.dto;

public class EmployeeDTO {

    private Long id;
    private String name;
    private Boolean active;
    private String department;
    private Long dpId;

    public EmployeeDTO(Long id, String name, Boolean active, String departament, Long dpId) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.department = departament;
        this.dpId = dpId;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getDpId() {
        return dpId;
    }

    public void setDpId(Long dpId) {
        this.dpId = dpId;
    }
}
