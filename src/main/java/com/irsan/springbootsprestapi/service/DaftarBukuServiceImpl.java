package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.DaftarBukuLihatRequest;
import com.irsan.springbootsprestapi.model.DaftarBukuResponse;
import com.irsan.springbootsprestapi.model.DaftarBukuSimpanRequest;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import com.irsan.springbootsprestapi.utils.DBHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
        Object[] params = {request.getNamaBuku(), request.getNamaBuku(), request.getNoIsbn(), request.getDeskripsi(), request.getKeterangan()};
        String data = getSingleResult("spDaftarBukuSimpan", params).toString();
        return BaseResponse.ok(data);
    }

    @Override
    public BaseResponse<?> daftarBukuLihat(DaftarBukuLihatRequest request, HttpServletRequest httpRequest) {
        Object[] params = {request.getSearchGlobal(), request.getPageIn(), request.getLimit()};
        String data = getSingleResult("spDaftarBukuLihat", params).toString();
        return BaseResponse.ok(data);
    }

    @Override
    public BaseResponse<?> daftarBukuLihatv2(DaftarBukuLihatRequest request, HttpServletRequest httpRequest) {
        Object[] params = {request.getSearchGlobal(), request.getPageIn(), request.getLimit()};
        List<DaftarBukuResponse> data = getResultList("spDaftarBukuLihatv2", params);
        return BaseResponse.ok(data);

    }

}
