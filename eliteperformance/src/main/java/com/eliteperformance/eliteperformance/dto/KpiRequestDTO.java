package com.eliteperformance.eliteperformance.dto;

import jakarta.validation.constraints.*;

public class KpiRequestDTO {

    @NotNull
    private Long employeeId;

    @NotNull
    private Integer reviewYear;

    @DecimalMin("0.0") @DecimalMax("25.0")
    private double taskCompletion;

    @DecimalMin("0.0") @DecimalMax("15.0")
    private double attendance;

    @DecimalMin("0.0") @DecimalMax("15.0")
    private double teamCollaboration;

    @DecimalMin("0.0") @DecimalMax("15.0")
    private double problemSolving;

    @DecimalMin("0.0") @DecimalMax("10.0")
    private double communication;

    @DecimalMin("0.0") @DecimalMax("10.0")
    private double leadership;

    @DecimalMin("0.0") @DecimalMax("10.0")
    private double clientSatisfaction;

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public Integer getReviewYear() { return reviewYear; }
    public void setReviewYear(Integer reviewYear) { this.reviewYear = reviewYear; }
    public double getTaskCompletion() { return taskCompletion; }
    public void setTaskCompletion(double taskCompletion) { this.taskCompletion = taskCompletion; }
    public double getAttendance() { return attendance; }
    public void setAttendance(double attendance) { this.attendance = attendance; }
    public double getTeamCollaboration() { return teamCollaboration; }
    public void setTeamCollaboration(double teamCollaboration) { this.teamCollaboration = teamCollaboration; }
    public double getProblemSolving() { return problemSolving; }
    public void setProblemSolving(double problemSolving) { this.problemSolving = problemSolving; }
    public double getCommunication() { return communication; }
    public void setCommunication(double communication) { this.communication = communication; }
    public double getLeadership() { return leadership; }
    public void setLeadership(double leadership) { this.leadership = leadership; }
    public double getClientSatisfaction() { return clientSatisfaction; }
    public void setClientSatisfaction(double clientSatisfaction) { this.clientSatisfaction = clientSatisfaction; }
}