package com.eliteperformance.eliteperformance.controller;

import com.eliteperformance.eliteperformance.dto.BonusResponseDTO;
import com.eliteperformance.eliteperformance.dto.KpiRequestDTO;
import com.eliteperformance.eliteperformance.service.PerformanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController {

    private final PerformanceService service;

    public PerformanceController(PerformanceService service) {
        this.service = service;
    }

    // POST /api/performances/calculate
    @PostMapping("/calculate")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
public ResponseEntity<BonusResponseDTO> calculate(
        @Valid @RequestBody KpiRequestDTO dto) { 

        BonusResponseDTO result = service.calculateBonus(dto);
        return ResponseEntity.ok(result);
    }


    // ADMIN — all employee bonus can see
    @GetMapping("/all-bonuses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllBonuses() {
        List<BonusResponseDTO> result = service.getAllBonuses();
        return ResponseEntity.ok(result);
    }

    // EMPLOYEE — only own bonus can see
    @GetMapping("/my-bonus/{employeeId}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> getMyBonus(@PathVariable Long employeeId) {
        List<BonusResponseDTO> result = service.getBonusByEmployeeId(employeeId);
        return ResponseEntity.ok(result);
    }
}

