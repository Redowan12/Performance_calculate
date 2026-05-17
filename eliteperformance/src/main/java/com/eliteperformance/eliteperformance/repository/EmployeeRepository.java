package com.eliteperformance.eliteperformance.repository;

import com.eliteperformance.eliteperformance.model.Employee;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public EmployeeRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Employee> findById(Long employeeId) {
        String sql = "SELECT * FROM employees WHERE employee_id = :employeeId";
        Map<String, Object> params = Collections.singletonMap("employeeId", employeeId);

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Employee emp = new Employee();
            emp.setEmployeeId(rs.getLong("employee_id"));
            emp.setName(rs.getString("name"));
            emp.setDesignation(rs.getString("designation"));
            emp.setBaseSalary(rs.getDouble("base_salary"));
            emp.setRole(rs.getString("role"));
            if (rs.getDate("last_promotion_date") != null)
                emp.setLastPromotionDate(rs.getDate("last_promotion_date").toLocalDate());
            return emp;
        }).stream().findFirst();
    }
}