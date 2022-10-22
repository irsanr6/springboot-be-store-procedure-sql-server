package com.irsan.springbootsprestapi.utils;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DBHandler {

    @Autowired
    private static EntityManager entityManager;

    public static <T> List<T> getResultList(String spName, Object[] params) {
        String state = "";
        for (int i = 1; i <= params.length; i++) {
            state += "?" + i + ",";
        }
        if (params.length > 0) {
            state = state.substring(0, state.length() - 1);
        } else {
            state = "";
        }
        String sql = "exec " + spName + state;
        Query query = entityManager.createNativeQuery(sql);
        for (int i = 1; i <= params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return (List<T>) query.getResultList();
    }

    public static Object getSingleResult(String spName, Object[] params) {
        String state = "";
        for (int i = 1; i <= params.length; i++) {
            state += "?" + i + ",";
        }
        if (params.length > 0) {
            state = state.substring(0, state.length() - 1);
        } else {
            state = "";
        }
        String sql = "exec " + spName + state;
        Query query = entityManager.createNativeQuery(sql);
        for (int i = 1; i <= params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getSingleResult().toString();
    }
}
