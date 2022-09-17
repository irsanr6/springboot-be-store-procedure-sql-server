package com.irsan.springbootsprestapi.controller;

import com.irsan.springbootsprestapi.model.PinjamSimpanRequest;
import com.irsan.springbootsprestapi.service.PeminjamanBukuService;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@RestController
@RequestMapping("api/v1/peminjamanBuku")
public class PeminjamanBukuController {

    @Resource
    private PeminjamanBukuService peminjamanBukuService;

    @PostMapping("/pinjam")
    public BaseResponse<?> pinjamSimpan(@RequestBody PinjamSimpanRequest pinjamSimpanRequest,
                                        HttpServletRequest httpServletRequest) {
        return peminjamanBukuService.pinjamSimpan(pinjamSimpanRequest, httpServletRequest);
    }

    @GetMapping("/kembali")
    public BaseResponse<?> kembaliSimpan(@RequestParam(name = "peminjamanId") Integer peminjamanId,
                                         HttpServletRequest httpServletRequest) {
        return peminjamanBukuService.kembaliSimpan(peminjamanId, httpServletRequest);
    }

    @GetMapping("/daftarPeminjaman")
    public BaseResponse<?> daftarPeminjamanLihat(@RequestParam(name = "statusPeminjamanId") String statusPeminjamanId,
                                                 @RequestParam(name = "pageIn") Integer pageIn,
                                                 @RequestParam(name = "limit") Integer limit,
                                                 HttpServletRequest httpServletRequest) {
        return peminjamanBukuService.daftarPeminjamanLihat(statusPeminjamanId, pageIn, limit, httpServletRequest);
    }

}
