package com.eliteperformance.eliteperformance.dto;

public class BonusResponseDTO {
    private Long employeeId;
    private String employeeName;
    private int reviewYear;
    private double totalKpiScore;
    private String category;
    private double bonusPercentage;
    private double baseSalary;
    private double bonusAmount;
    private double totalCompensation;

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public int getReviewYear() { return reviewYear; }
    public void setReviewYear(int reviewYear) { this.reviewYear = reviewYear; }
    public double getTotalKpiScore() { return totalKpiScore; }
    public void setTotalKpiScore(double totalKpiScore) { this.totalKpiScore = totalKpiScore; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getBonusPercentage() { return bonusPercentage; }
    public void setBonusPercentage(double bonusPercentage) { this.bonusPercentage = bonusPercentage; }
    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }
    public double getBonusAmount() { return bonusAmount; }
    public void setBonusAmount(double bonusAmount) { this.bonusAmount = bonusAmount; }
    public double getTotalCompensation() { return totalCompensation; }
    public void setTotalCompensation(double totalCompensation) { this.totalCompensation = totalCompensation; }
}