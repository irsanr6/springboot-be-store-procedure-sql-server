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
public class MemberPerpusData {

    private String memberId;
    private String namaLengkap;
    private String username;
    private String email;
    private String ttl;
    private String nik;
    private String alamatDomisili;
    private String nomorHandphone;
    private String namaRole;
    private String isAktif;

}
