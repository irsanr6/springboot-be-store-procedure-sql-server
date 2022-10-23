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
@NoArgsConstructor
@AllArgsConstructor
public class PinjamSimpanRequest {

    private String bukuId;
    private String keterangan;

}
