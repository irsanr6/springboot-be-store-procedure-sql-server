package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.DaftarBukuLihatRequest;
import com.irsan.springbootsprestapi.model.DaftarBukuResponse;
import com.irsan.springbootsprestapi.model.DaftarBukuSimpanRequest;
import com.irsan.springbootsprestapi.model.MemberPerpusData;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import com.irsan.springbootsprestapi.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.irsan.springbootsprestapi.utils.Helpers.getString;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Service
@Slf4j
public class DaftarBukuServiceImpl implements DaftarBukuService {

    @Resource
    private EntityManager entityManager;

    @Override
    public BaseResponse<?> daftarBukuSimpan(DaftarBukuSimpanRequest request, HttpServletRequest httpRequest) {

        getRelativePath(httpRequest);

        Query query = entityManager.createNativeQuery("exec spDaftarBukuSimpan ?1, ?2, ?3, ?4, ?5");
        query.setParameter(1, request.getBukuId());
        query.setParameter(2, request.getNamaBuku());
        query.setParameter(3, request.getNoIsbn());
        query.setParameter(4, request.getDeskripsi());
        query.setParameter(5, request.getKeterangan());

        Object data = query.getSingleResult();

        return BaseResponse.ok(data.toString());

    }

    @Override
    public BaseResponse<?> daftarBukuLihat(DaftarBukuLihatRequest request, HttpServletRequest httpRequest) {

        getRelativePath(httpRequest);

        Query query = entityManager.createNativeQuery("exec spDaftarBukuLihat ?1, ?2, ?3");
        query.setParameter(1, request.getSearchGlobal());
        query.setParameter(2, request.getPageIn());
        query.setParameter(3, request.getLimit());

        Object data = query.getSingleResult();

//        List<DaftarBukuResponse> responses = new ArrayList<>();
//        String delimiter = "@@";
//        List<String> dataArr = new ArrayList<>();
//        StringTokenizer st = new StringTokenizer(data.toString(), delimiter);
//        while (st.hasMoreTokens()) {
//            dataArr.add(st.nextToken());
//        }
//        for (String data2 :
//                dataArr) {
//            String delimiter2 = "##";
//            List<String> dataArr2 = new ArrayList<>();
//            StringTokenizer st2 = new StringTokenizer(data2, delimiter2);
//            while (st2.hasMoreTokens()) {
//                dataArr2.add(st2.nextToken());
//            }
//            DaftarBukuResponse response = DaftarBukuResponse.builder()
//                    .bukuId(dataArr2.get(0))
//                    .namaBuku(dataArr2.get(1))
//                    .noIsbn(dataArr2.get(2))
//                    .deskripsi(dataArr2.get(3))
//                    .keterangan(dataArr2.get(4))
//                    .build();
//            responses.add(response);
//        }

        return BaseResponse.ok(data.toString());

    }

    @Override
    public BaseResponse<?> daftarBukuLihatv2(DaftarBukuLihatRequest request, HttpServletRequest httpRequest) {

        getRelativePath(httpRequest);

        List<DaftarBukuResponse> responses = new ArrayList<>();
        Query query = entityManager.createNativeQuery("exec spDaftarBukuLihatv2 ?1, ?2, ?3");
        query.setParameter(1, request.getSearchGlobal());
        query.setParameter(2, request.getPageIn());
        query.setParameter(3, request.getLimit());

        List<Object[]> results = query.getResultList();

        results.forEach(col -> {
            responses.add(new DaftarBukuResponse(
                    getString(col[0]),
                    getString(col[1]),
                    getString(col[2]),
                    getString(col[3]),
                    getString(col[4])
            ));
        });
        return BaseResponse.ok(responses);

    }

    private static void getRelativePath(HttpServletRequest httpRequest) {
        MemberPerpusData memberPerpusData = SessionUtil.getMemberPerpusData(httpRequest);
        String uri = httpRequest.getRequestURI();
        log.info("memberPerpusData -> {}", memberPerpusData);
        log.info("uri -> {}", uri);
    }
}
