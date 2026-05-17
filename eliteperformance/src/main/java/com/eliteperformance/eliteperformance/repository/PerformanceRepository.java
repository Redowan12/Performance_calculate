package com.eliteperformance.eliteperformance.repository;

import com.eliteperformance.eliteperformance.dto.KpiRequestDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PerformanceRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PerformanceRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Duplicate review check
    public boolean existsByEmployeeAndYear(Long employeeId, int year) {
        String sql = "SELECT COUNT(*) FROM performance_reviews " +
                "WHERE employee_id = :empId AND review_year = :year";
        Map<String, Object> params = Map.of("empId", employeeId, "year", year);
        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }

    public void save(KpiRequestDTO dto, double totalKpi) {
        String sql = "INSERT INTO performance_reviews " +
                "(employee_id, review_year, task_completion, attendance, " +
                "team_collaboration, problem_solving, communication, " +
                "leadership, client_satisfaction, total_kpi_score) " +
                "VALUES " +
                "(:empId, :year, :tc, :at, :tcb, :ps, :com, :ld, :cs, :total)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("empId", dto.getEmployeeId())
                .addValue("year",  dto.getReviewYear())
                .addValue("tc",    dto.getTaskCompletion())
                .addValue("at",    dto.getAttendance())
                .addValue("tcb",   dto.getTeamCollaboration())
                .addValue("ps",    dto.getProblemSolving())
                .addValue("com",   dto.getCommunication())
                .addValue("ld",    dto.getLeadership())
                .addValue("cs",    dto.getClientSatisfaction())
                .addValue("total", totalKpi);

        jdbcTemplate.update(sql, params);
    }
}