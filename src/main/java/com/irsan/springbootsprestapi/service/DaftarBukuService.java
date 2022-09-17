package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.DaftarBukuLihatRequest;
import com.irsan.springbootsprestapi.model.DaftarBukuSimpanRequest;
import com.irsan.springbootsprestapi.utils.BaseResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
public interface DaftarBukuService {
    BaseResponse<?> daftarBukuSimpan(DaftarBukuSimpanRequest request, HttpServletRequest httpRequest);

    BaseResponse<?> daftarBukuLihat(DaftarBukuLihatRequest request, HttpServletRequest httpRequest);

    BaseResponse<?> daftarBukuLihatv2(DaftarBukuLihatRequest request, HttpServletRequest httpRequest);
}
