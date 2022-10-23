package com.irsan.springbootsprestapi;

import com.irsan.springbootsprestapi.mapper.ColumnMapper;
import com.irsan.springbootsprestapi.model.DaftarBukuLihatRequest;
import com.irsan.springbootsprestapi.model.PinjamSimpanRequest;
import com.irsan.springbootsprestapi.utils.DBHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class SpringbootRestApiSqlServerStoreProcedureApplicationTests extends DBHandler {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static final class DataRequest {
        private Long bukuId;
        private String namaBuku;
        private Long noIsbn;
        private String deskripsi;
        private String keterangan;
    }

    @Test
    void testSP() {
        final DataRequest request = DataRequest.builder()
                .bukuId(4L)
                .namaBuku("Belajar Pemrograman SOLID")
                .noIsbn(9876127158678976L)
                .deskripsi("Dalam buku ini kita akan belajar pemrograman solid")
                .keterangan("Bukunya sangat bagus")
                .build();
        String sql = "exec spDaftarBukuSimpan ?1, ?2, ?3, ?4, ?5";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, request.getBukuId());
        query.setParameter(2, request.getNamaBuku());
        query.setParameter(3, request.getNoIsbn());
        query.setParameter(4, request.getDeskripsi());
        query.setParameter(5, request.getKeterangan());

        Object data = query.getSingleResult();
        System.out.println(data.toString());
    }

    @Test
    void testObject() {
        Object[] data = {"irsan", "ramadhan", "okee", "lah"};

        PinjamSimpanRequest request = new PinjamSimpanRequest("BKU20229000007", "Pinjam kesekian kali atas nama amri maulana");
        Object[] objRequest = {request.getBukuId(), request.getKeterangan()};

        String spName = "spDaftarBukuLihatv2";

        String state = "";

        for (int i = 1; i <= objRequest.length; i++) {
            state += " ?" + i + ",";
        }
        if (objRequest.length > 0) {
            state = state.substring(0, state.length() - 1);
            System.out.println(state);
        } else {
            state = "";
        }
        String sql = "exec " + spName + state;
        System.out.println(sql);
        for (int j = 1; j <= objRequest.length; j++) {
            System.out.println(j);
        }

        for (int i = 0; i < objRequest.length; i++) {
            int num = i + 1;
            System.out.println(num + " " + objRequest[i]);
        }
    }

    @Test
    void testSp2() {
        DaftarBukuLihatRequest request = new DaftarBukuLihatRequest("", 1, 10);
//        String sql = "exec spDaftarBukuLihatv2 ?1, ?2, ?3";
//        Query query = entityManager.createNativeQuery(sql);
//        query.setParameter(1, request.getSearchGlobal());
//        query.setParameter(2, request.getPageIn());
//        query.setParameter(3, request.getLimit());

        Object[] params = {request.getSearchGlobal(), request.getPageIn(), request.getLimit()};
        String spName = "spDaftarBukuLihatv2";
        List<HashMap<String, String>> data = getResultList(spName, params);
        System.out.println(data);
    }

    @Test
    void name() {
    }

    @Test
    void testSp3() {
        DaftarBukuLihatRequest request = new DaftarBukuLihatRequest("", 1, 10);
        Object[] params = {request.getSearchGlobal(), request.getPageIn(), request.getLimit()};
        String spName = "spDaftarBukuLihatv2";
        HashMap<String, String> hashMap = getStringStringHashMap(params, spName);
        System.out.println("data " + hashMap);
    }

    private HashMap<String, String> getStringStringHashMap(Object[] params, String spName) {
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
        System.out.println("from test " + sql.getClass());
        return jdbcTemplate.queryForObject(sql, params, new ColumnMapper());
    }

}
