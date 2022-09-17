package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.PinjamSimpanRequest;
import com.irsan.springbootsprestapi.utils.BaseResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
public interface PeminjamanBukuService {
    BaseResponse<?> pinjamSimpan(PinjamSimpanRequest pinjamSimpanRequest, HttpServletRequest httpServletRequest);

    BaseResponse<?> kembaliSimpan(Integer peminjamanId, HttpServletRequest httpServletRequest);

    BaseResponse<?> daftarPeminjamanLihat(String statusPeminjamanId, Integer pageIn, Integer limit, HttpServletRequest httpServletRequest);
}
