package com.irsan.springbootsprestapi.controller;

import com.irsan.springbootsprestapi.model.DaftarBukuLihatRequest;
import com.irsan.springbootsprestapi.model.DaftarBukuSimpanRequest;
import com.irsan.springbootsprestapi.service.DaftarBukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@RestController
@RequestMapping("api/v1/daftarBuku")
public class DaftarBukuController {

    @Autowired
    private DaftarBukuService daftarBukuService;

    @PostMapping("/bukuSimpan")
    public ResponseEntity<?> daftarBukuSimpan(@RequestBody DaftarBukuSimpanRequest request) {
        return daftarBukuService.daftarBukuSimpan(request);
    }

    @PostMapping("/bukuLihat")
    public ResponseEntity<?> daftarBukuLihat(@RequestBody DaftarBukuLihatRequest request) {
        return daftarBukuService.daftarBukuLihat(request);
    }

    @PostMapping("/bukuLihatv2")
    public ResponseEntity<?> daftarBukuLihatv2(@RequestBody DaftarBukuLihatRequest request) {
        return daftarBukuService.daftarBukuLihatv2(request);
    }

}
