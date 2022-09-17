package com.irsan.springbootsprestapi.config;

import com.irsan.springbootsprestapi.model.FieldErrorModel;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldErrorModel> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> new FieldErrorModel(x.getField(), x.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(BaseResponse.error("Validation Error", errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> handleGlobalException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.ok(BaseResponse.error("Terjadi Kesalahan di Server"));
    }

}
