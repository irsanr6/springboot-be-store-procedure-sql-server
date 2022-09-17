package com.irsan.springbootsprestapi.utils;

import com.irsan.springbootsprestapi.model.MemberPerpusData;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
public class SessionUtil {

    public static MemberPerpusData getMemberPerpusData(HttpServletRequest request) {
        return (MemberPerpusData) request.getAttribute(Constant.HEADER_DATA);
    }

}
