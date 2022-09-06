package com.irsan.springbootsprestapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@SpringBootTest
class SpringbootRestApiSqlServerStoreProcedureApplicationTests {

    @Autowired
    private EntityManager entityManager;

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
        Query query = entityManager.createNativeQuery("exec spDaftarBukuSimpan ?1, ?2, ?3, ?4, ?5");
        query.setParameter(1, request.getBukuId());
        query.setParameter(2, request.getNamaBuku());
        query.setParameter(3, request.getNoIsbn());
        query.setParameter(4, request.getDeskripsi());
        query.setParameter(5, request.getKeterangan());

        Object data = query.getSingleResult();
        System.out.println(data.toString());
    }

}
