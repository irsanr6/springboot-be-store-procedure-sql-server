package com.irsan.springbootsprestapi.controller;

import com.irsan.springbootsprestapi.model.LoginRequest;
import com.irsan.springbootsprestapi.model.RegisterRequest;
import com.irsan.springbootsprestapi.service.AuthService;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/signin")
    public BaseResponse<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws BadCredentialsException {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public BaseResponse<?> registerUser(@RequestBody RegisterRequest registerRequest) throws IOException {
        return authService.registerUser(registerRequest);
    }

}
