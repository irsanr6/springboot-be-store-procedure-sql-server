package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.MemberPerpusData;
import com.irsan.springbootsprestapi.model.PinjamSimpanRequest;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import com.irsan.springbootsprestapi.utils.DBHandler;
import com.irsan.springbootsprestapi.utils.SessionUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Service
public class PeminjamanBukuServiceImpl extends DBHandler implements PeminjamanBukuService {

    @Override
    public BaseResponse<?> pinjamSimpan(PinjamSimpanRequest pinjamSimpanRequest, HttpServletRequest httpServletRequest) {
        MemberPerpusData memberPerpusData = SessionUtil.getMemberPerpusData(httpServletRequest);
        Object[] params = {pinjamSimpanRequest.getBukuId(), memberPerpusData.getMemberId(), pinjamSimpanRequest.getKeterangan()};
        HashMap<String, String> data = getSingleResult("spPeminjamanBukuSimpan", params);
        return BaseResponse.ok(data.get("data"));
    }

    @Override
    public BaseResponse<?> kembaliSimpan(Integer peminjamanId, HttpServletRequest httpServletRequest) {
        MemberPerpusData memberPerpusData = SessionUtil.getMemberPerpusData(httpServletRequest);
        Object[] params = {peminjamanId, memberPerpusData.getMemberId()};
        HashMap<String, String> data = getSingleResult("spPengembalianBukuSimpan", params);
        return BaseResponse.ok(data.get("data"));
    }

    @Override
    public BaseResponse<?> daftarPeminjamanLihat(String statusPeminjamanId, Integer pageIn, Integer limit, HttpServletRequest httpServletRequest) {
        MemberPerpusData memberPerpusData = SessionUtil.getMemberPerpusData(httpServletRequest);
        Object[] params = {memberPerpusData.getMemberId(), statusPeminjamanId, pageIn, limit};
        List<HashMap<String, String>> data = getResultList("spMemberDaftarPeminjamanBukuLihat", params);
        return BaseResponse.ok(data);
    }
}
