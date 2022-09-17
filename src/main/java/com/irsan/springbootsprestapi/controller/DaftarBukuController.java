package com.irsan.springbootsprestapi.controller;

import com.irsan.springbootsprestapi.model.DaftarBukuLihatRequest;
import com.irsan.springbootsprestapi.model.DaftarBukuSimpanRequest;
import com.irsan.springbootsprestapi.service.DaftarBukuService;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@RestController
@RequestMapping("api/v1/daftarBuku")
public class DaftarBukuController {

    @Resource
    private DaftarBukuService daftarBukuService;

    @PostMapping("/bukuSimpan")
    public BaseResponse<?> daftarBukuSimpan(@RequestBody DaftarBukuSimpanRequest request,
                                            HttpServletRequest httpRequest) {
        return daftarBukuService.daftarBukuSimpan(request, httpRequest);
    }

    @PostMapping("/bukuLihat")
    public BaseResponse<?> daftarBukuLihat(@RequestBody DaftarBukuLihatRequest request,
                                           HttpServletRequest httpRequest) {
        return daftarBukuService.daftarBukuLihat(request, httpRequest);
    }

    @PostMapping("/bukuLihatv2")
    public BaseResponse<?> daftarBukuLihatv2(@RequestBody DaftarBukuLihatRequest request,
                                             HttpServletRequest httpRequest) {
        return daftarBukuService.daftarBukuLihatv2(request, httpRequest);
    }

}
