package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.DaftarBukuLihatRequest;
import com.irsan.springbootsprestapi.model.DaftarBukuSimpanRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
public interface DaftarBukuService {
    ResponseEntity<?> daftarBukuSimpan(DaftarBukuSimpanRequest request);

    ResponseEntity<?> daftarBukuLihat(DaftarBukuLihatRequest request);

    ResponseEntity<?> daftarBukuLihatv2(DaftarBukuLihatRequest request);
}
