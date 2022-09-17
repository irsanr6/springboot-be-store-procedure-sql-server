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
public class DaftarPeminjamanLihatResponse {

    private String peminjamanId;
    private String namaBuku;
    private String noIsbn;
    private String deskripsi;
    private String keterangan;

}
