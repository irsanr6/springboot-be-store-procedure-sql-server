package com.irsan.springbootsprestapi.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1000177997972510411L;
    private int status;
    private boolean success;
    private String message;
    private T data;

    public static <T> BaseResponse<T> ok(T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .status(200)
                .message("OK")
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> ok(String message, T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .status(200)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> error(String message) {
        return BaseResponse.<T>builder()
                .status(500)
                .success(false)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(null)
                .build();
    }

    public static <T> BaseResponse<T> error200(String message) {
        return BaseResponse.<T>builder()
                .status(200)
                .success(false)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(null)
                .build();
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        return BaseResponse.<T>builder()
                .status(500)
                .success(false)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> create(int status, boolean success, String message, T data) {
        return BaseResponse.<T>builder()
                .status(status)
                .success(success)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(data)
                .build();
    }

}
