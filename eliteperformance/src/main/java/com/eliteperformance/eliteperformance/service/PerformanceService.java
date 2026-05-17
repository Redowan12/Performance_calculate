package com.eliteperformance.eliteperformance.service;

import com.eliteperformance.eliteperformance.dto.BonusResponseDTO;
import com.eliteperformance.eliteperformance.dto.KpiRequestDTO;
import com.eliteperformance.eliteperformance.model.Employee;
import com.eliteperformance.eliteperformance.repository.BonusRepository;
import com.eliteperformance.eliteperformance.repository.EmployeeRepository;
import com.eliteperformance.eliteperformance.repository.PerformanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class PerformanceService {

    private final EmployeeRepository employeeRepo;
    private final PerformanceRepository performanceRepo;
    private final BonusRepository bonusRepo;

    public PerformanceService(EmployeeRepository employeeRepo,
                              PerformanceRepository performanceRepo,
                              BonusRepository bonusRepo) {
        this.employeeRepo = employeeRepo;
        this.performanceRepo = performanceRepo;
        this.bonusRepo = bonusRepo;
    }

    @Transactional
    public BonusResponseDTO calculateBonus(KpiRequestDTO dto) {

        // Employee checking
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException(
                        "Employee not found: " + dto.getEmployeeId()));

        //
        if (performanceRepo.existsByEmployeeAndYear(
                dto.getEmployeeId(), dto.getReviewYear())) {
            throw new RuntimeException(
                    "Review already exists for year: " + dto.getReviewYear());
        }

        //  Step 3: Total KPI Score calculate
        double totalKpi = dto.getTaskCompletion()
                + dto.getAttendance()
                + dto.getTeamCollaboration()
                + dto.getProblemSolving()
                + dto.getCommunication()
                + dto.getLeadership()
                + dto.getClientSatisfaction();

        //  Step 4: Category and Bonus Percentage
        String category;
        double bonusPct;

        if (totalKpi >= 90) {
            category = "Gold Tier";
            bonusPct = 20.0;
        } else if (totalKpi >= 75) {
            category = "Silver Tier";
            bonusPct = 12.0;
        } else if (totalKpi >= 60) {
            category = "Bronze Tier";
            bonusPct = 5.0;
        } else {
            category = "No Tier";
            bonusPct = 0.0;
        }

        // ✅ Step 5: Bonus Amount and Total Compensation calculate
        double baseSalary = employee.getBaseSalary();
        double bonusAmount = baseSalary * bonusPct / 100.0;
        double totalCompensation = baseSalary + bonusAmount;

        // ✅ Database এ Performance Review save
        performanceRepo.save(dto, totalKpi);

        // ✅ Response create
        BonusResponseDTO response = new BonusResponseDTO();
        response.setEmployeeId(employee.getEmployeeId());
        response.setEmployeeName(employee.getName());
        response.setReviewYear(dto.getReviewYear());
        response.setTotalKpiScore(totalKpi);
        response.setCategory(category);
        response.setBonusPercentage(bonusPct);
        response.setBaseSalary(baseSalary);
        response.setBonusAmount(bonusAmount);
        response.setTotalCompensation(totalCompensation);

        //  Bonus Record save
        bonusRepo.save(response);

        return response;
    }


    public List<BonusResponseDTO> getAllBonuses() {
        return bonusRepo.findAll();
    }

    public List<BonusResponseDTO> getBonusByEmployeeId(Long employeeId) {
        return bonusRepo.findByEmployeeId(employeeId);
    }
}