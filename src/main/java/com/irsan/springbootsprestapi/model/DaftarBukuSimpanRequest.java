package com.irsan.springbootsprestapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DaftarBukuSimpanRequest {

    private String bukuId;
    private String namaBuku;
    private Long noIsbn;
    private String deskripsi;
    private String keterangan;

}
