package com.irsan.springbootsprestapi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
public class Helpers {

    public static String getString(Object obj){
        if (obj == null) return "";
        else return String.valueOf(obj);
    }

    public static String generatedMemberId() {
        String prefix = "WPK";
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String strDate = dateFormat.format(date);
        return prefix.concat(strDate);
    }

}
