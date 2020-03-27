package com.charter.assignment.dao;

import com.charter.assignment.api.SaleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SalesDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void submitSale(SaleRequest saleRequest) {
        namedParameterJdbcTemplate.update("INSERT INTO SALE (AMOUNT, SALE_DT, CUST_ID) VALUES (:amount, :saledate, :customerId)",
                new MapSqlParameterSource().addValue("amount", saleRequest.getSale())
                        .addValue("saledate", saleRequest.getDateTime())
                        .addValue("customerId", saleRequest.getCustomerId()));
    }

    public List<SaleRequest> getAllSales(String customerId) {
        return namedParameterJdbcTemplate.query("SELECT * FROM SALE WHERE CUST_ID = :customerId", new MapSqlParameterSource().addValue("customerId", customerId),
                new RowMapper<SaleRequest>() {
                    @Override
                    public SaleRequest mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new SaleRequest(resultSet.getInt("AMOUNT"), resultSet.getDate("SALE_DT"), resultSet.getString("CUST_ID"));
                    }
                });
    }


    public List<SaleRequest> getSalesInPeriod(LocalDate startDate, LocalDate endDate, String customerId) {
        return namedParameterJdbcTemplate.query("SELECT * FROM SALE WHERE SALE_DT > :startDate AND SALE_ST < :endDate AND CUST_ID = :customerId", new MapSqlParameterSource().addValue("startDate", startDate)
                        .addValue("endDate", endDate)
                        .addValue("customerId", customerId),
                new RowMapper<SaleRequest>() {
                    @Override
                    public SaleRequest mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new SaleRequest(resultSet.getInt("AMOUNT"), resultSet.getDate("SALE_DT"), resultSet.getString("CUST_ID"));
                    }
                });
    }
}
