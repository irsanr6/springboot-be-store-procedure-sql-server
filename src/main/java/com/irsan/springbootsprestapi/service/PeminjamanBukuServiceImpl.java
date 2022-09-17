package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.DaftarPeminjamanLihatResponse;
import com.irsan.springbootsprestapi.model.MemberPerpusData;
import com.irsan.springbootsprestapi.model.PinjamSimpanRequest;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import com.irsan.springbootsprestapi.utils.SessionUtil;
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
public class PeminjamanBukuServiceImpl implements PeminjamanBukuService {

    @Resource
    private EntityManager entityManager;

    @Override
    public BaseResponse<?> pinjamSimpan(PinjamSimpanRequest pinjamSimpanRequest, HttpServletRequest httpServletRequest) {
        MemberPerpusData memberPerpusData = SessionUtil.getMemberPerpusData(httpServletRequest);
        Query query = entityManager.createNativeQuery("exec spPeminjamanBukuSimpan ?1, ?2, ?3");
        query.setParameter(1, pinjamSimpanRequest.getBukuId());
        query.setParameter(2, memberPerpusData.getMemberId());
        query.setParameter(3, pinjamSimpanRequest.getKeterangan());
        Object data = query.getSingleResult();

        return BaseResponse.ok(data.toString());
    }

    @Override
    public BaseResponse<?> kembaliSimpan(Integer peminjamanId, HttpServletRequest httpServletRequest) {
        MemberPerpusData memberPerpusData = SessionUtil.getMemberPerpusData(httpServletRequest);
        Query query = entityManager.createNativeQuery("exec spPengembalianBukuSimpan ?1, ?2");
        query.setParameter(1, peminjamanId);
        query.setParameter(2, memberPerpusData.getMemberId());
        Object data = query.getSingleResult();

        return BaseResponse.ok(data.toString());
    }

    @Override
    public BaseResponse<?> daftarPeminjamanLihat(String statusPeminjamanId, Integer pageIn, Integer limit, HttpServletRequest httpServletRequest) {
        MemberPerpusData memberPerpusData = SessionUtil.getMemberPerpusData(httpServletRequest);
        List<DaftarPeminjamanLihatResponse> details = new ArrayList<>();
        Query query = entityManager.createNativeQuery("exec spMemberDaftarPeminjamanBukuLihat ?1, ?2, ?3, ?4");
        query.setParameter(1, memberPerpusData.getMemberId());
        query.setParameter(2, statusPeminjamanId);
        query.setParameter(3, pageIn);
        query.setParameter(4, limit);
        List<Object[]> results = query.getResultList();
        if (results.isEmpty() || results == null) {
            return BaseResponse.error200("Data tidak ditemukan");
        }
        results.forEach(col -> {
            details.add(DaftarPeminjamanLihatResponse.builder()
                    .peminjamanId(getString(col[0]))
                    .namaBuku(getString(col[1]))
                    .noIsbn(getString(col[2]))
                    .deskripsi(getString(col[3]))
                    .keterangan(getString(col[4]))
                    .build());
        });

        return BaseResponse.ok(details);
    }
}
