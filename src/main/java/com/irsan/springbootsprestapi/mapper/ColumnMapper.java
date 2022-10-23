package com.irsan.springbootsprestapi.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ColumnMapper implements RowMapper<HashMap<String, String>> {
    @Override
    public HashMap<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
        int noOfColumnIndex = rs.getMetaData().getColumnCount();
        HashMap<String, String> hashMap = new HashMap<>();
        for (int columnIndex = 1; columnIndex <= noOfColumnIndex; columnIndex++) {
            String columnName = rs.getMetaData().getColumnName(columnIndex);
            Object columnValue = rs.getObject(columnIndex);
            if (columnValue != null) {
                hashMap.put(columnName.toLowerCase(), columnValue.toString());
            } else {
                hashMap.put(columnName.toLowerCase(), "");
            }
        }
        return hashMap;
    }
}
