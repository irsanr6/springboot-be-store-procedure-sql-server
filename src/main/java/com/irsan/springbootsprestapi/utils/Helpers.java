package com.irsan.springbootsprestapi.utils;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
public class Helpers {

    public static String getString(Object obj){
        if (obj == null) return "";
        else return String.valueOf(obj);
    }

}
