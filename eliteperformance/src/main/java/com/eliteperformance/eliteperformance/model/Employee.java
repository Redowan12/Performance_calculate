package com.eliteperformance.eliteperformance.model;

import java.time.LocalDate;

public class Employee {
    private Long employeeId;
    private String name;
    private String designation;
    private double baseSalary;
    private String role;
    private LocalDate lastPromotionDate;

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public LocalDate getLastPromotionDate() { return lastPromotionDate; }
    public void setLastPromotionDate(LocalDate d) { this.lastPromotionDate = d; }
}