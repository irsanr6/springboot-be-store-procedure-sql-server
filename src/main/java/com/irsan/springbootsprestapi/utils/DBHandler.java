package com.irsan.springbootsprestapi.utils;

import com.irsan.springbootsprestapi.mapper.ColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;

public class DBHandler {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<HashMap<String, String>> getResultList(String spName, Object[] params) {
        String state = "";
        for (int i = 1; i <= params.length; i++) {
            state += " ?,";
        }
        if (params.length > 0) {
            state = state.substring(0, state.length() - 1);
        } else {
            state = "";
        }
        String sql = "exec " + spName + state;
        return jdbcTemplate.query(sql, params, new ColumnMapper());
    }

    public HashMap<String, String> getSingleResult(String spName, Object[] params) {
        String state = "";
        for (int i = 1; i <= params.length; i++) {
            state += " ?,";
        }
        if (params.length > 0) {
            state = state.substring(0, state.length() - 1);
        } else {
            state = "";
        }
        String sql = "exec " + spName + state;
        return jdbcTemplate.queryForObject(sql, params, new ColumnMapper());
    }
}
