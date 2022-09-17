package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.LoginRequest;
import com.irsan.springbootsprestapi.model.RegisterRequest;
import com.irsan.springbootsprestapi.utils.BaseResponse;

import java.io.IOException;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
public interface AuthService {
    BaseResponse<?> authenticateUser(LoginRequest loginRequest);

    BaseResponse<?> registerUser(RegisterRequest registerRequest) throws IOException;
}
