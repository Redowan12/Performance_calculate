package com.eliteperformance.eliteperformance.repository;

import com.eliteperformance.eliteperformance.dto.BonusResponseDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BonusRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BonusRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(BonusResponseDTO dto) {
        String sql = "INSERT INTO bonus_records " +
                "(employee_id, review_year, total_kpi_score, category, " +
                "bonus_percentage, bonus_amount, total_compensation) " +
                "VALUES " +
                "(:empId, :year, :kpi, :cat, :pct, :bonus, :total)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("empId",  dto.getEmployeeId())
                .addValue("year",   dto.getReviewYear())
                .addValue("kpi",    dto.getTotalKpiScore())
                .addValue("cat",    dto.getCategory())
                .addValue("pct",    dto.getBonusPercentage())
                .addValue("bonus",  dto.getBonusAmount())
                .addValue("total",  dto.getTotalCompensation());

        jdbcTemplate.update(sql, params);
    }


    // ADMIN — সব bonus record
    public List<BonusResponseDTO> findAll() {
        String sql = "SELECT b.*, e.name as employee_name, e.base_salary " +
                "FROM bonus_records b " +
                "JOIN employees e ON b.employee_id = e.employee_id";

        return jdbcTemplate.query(sql, new MapSqlParameterSource(), (rs, rowNum) -> {
            BonusResponseDTO dto = new BonusResponseDTO();
            dto.setEmployeeId(rs.getLong("employee_id"));
            dto.setEmployeeName(rs.getString("employee_name"));
            dto.setReviewYear(rs.getInt("review_year"));
            dto.setTotalKpiScore(rs.getDouble("total_kpi_score"));
            dto.setCategory(rs.getString("category"));
            dto.setBonusPercentage(rs.getDouble("bonus_percentage"));
            dto.setBaseSalary(rs.getDouble("base_salary"));
            dto.setBonusAmount(rs.getDouble("bonus_amount"));
            dto.setTotalCompensation(rs.getDouble("total_compensation"));
            return dto;
        });
    }

    // EMPLOYEE — own bonus record
    public List<BonusResponseDTO> findByEmployeeId(Long employeeId) {
        String sql = "SELECT b.*, e.name as employee_name, e.base_salary " +
                "FROM bonus_records b " +
                "JOIN employees e ON b.employee_id = e.employee_id " +
                "WHERE b.employee_id = :empId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("empId", employeeId);

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            BonusResponseDTO dto = new BonusResponseDTO();
            dto.setEmployeeId(rs.getLong("employee_id"));
            dto.setEmployeeName(rs.getString("employee_name"));
            dto.setReviewYear(rs.getInt("review_year"));
            dto.setTotalKpiScore(rs.getDouble("total_kpi_score"));
            dto.setCategory(rs.getString("category"));
            dto.setBonusPercentage(rs.getDouble("bonus_percentage"));
            dto.setBaseSalary(rs.getDouble("base_salary"));
            dto.setBonusAmount(rs.getDouble("bonus_amount"));
            dto.setTotalCompensation(rs.getDouble("total_compensation"));
            return dto;
        });
    }


}