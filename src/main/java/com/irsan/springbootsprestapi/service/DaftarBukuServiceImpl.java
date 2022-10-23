package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.DaftarBukuLihatRequest;
import com.irsan.springbootsprestapi.model.DaftarBukuSimpanRequest;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import com.irsan.springbootsprestapi.utils.DBHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Service
@Slf4j
public class DaftarBukuServiceImpl extends DBHandler implements DaftarBukuService {

    @Override
    public BaseResponse<?> daftarBukuSimpan(DaftarBukuSimpanRequest request, HttpServletRequest httpRequest) {
        Object[] params = {request.getBukuId(), request.getNamaBuku(), request.getNoIsbn(), request.getDeskripsi(), request.getKeterangan()};
        HashMap<String, String> data = getSingleResult("spDaftarBukuSimpan", params);
        return BaseResponse.ok(data.get("data"));
    }

    @Override
    public BaseResponse<?> daftarBukuLihat(DaftarBukuLihatRequest request, HttpServletRequest httpRequest) {
        Object[] params = {request.getSearchGlobal(), request.getPageIn(), request.getLimit()};
        HashMap<String, String> data = getSingleResult("spDaftarBukuLihat", params);
        return BaseResponse.ok(data.get("data"));
    }

    @Override
    public BaseResponse<?> daftarBukuLihatv2(DaftarBukuLihatRequest request, HttpServletRequest httpRequest) {
        Object[] params = {request.getSearchGlobal(), request.getPageIn(), request.getLimit()};
        List<HashMap<String, String>> data = getResultList("spDaftarBukuLihatv2", params);
        return BaseResponse.ok(data);

    }

}
