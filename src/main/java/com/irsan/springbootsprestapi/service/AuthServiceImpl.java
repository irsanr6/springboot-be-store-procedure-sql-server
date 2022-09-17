package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.LoginRequest;
import com.irsan.springbootsprestapi.model.MemberPerpusData;
import com.irsan.springbootsprestapi.model.RegisterRequest;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import com.irsan.springbootsprestapi.utils.Helpers;
import com.irsan.springbootsprestapi.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.irsan.springbootsprestapi.utils.CompressionUtil.compressB64;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private EntityManager entityManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public BaseResponse<?> authenticateUser(LoginRequest loginRequest) {
        getStringBaseResponse(loginRequest);
        Query query = entityManager.createNativeQuery("exec spMemberPerpusDetailLihat ?1");
        query.setParameter(1, loginRequest.getUsernameOrEmail());
        List<?> data = Arrays.asList((Object[]) query.getSingleResult());
        MemberPerpusData memberPerpusData = MemberPerpusData.builder()
                .memberId(data.get(0).toString())
                .namaLengkap(data.get(1).toString())
                .username(data.get(2).toString())
                .email(data.get(3).toString())
                .ttl(data.get(4).toString())
                .nik(data.get(5).toString())
                .alamatDomisili(data.get(6).toString())
                .nomorHandphone(data.get(7).toString())
                .namaRole(data.get(8).toString())
                .isAktif(data.get(9).toString())
                .build();
        final String token = jwtTokenUtil.generateToken(memberPerpusData);
        return BaseResponse.ok("Your token access", token);
    }

    private void getStringBaseResponse(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException exception) {
            BaseResponse.error("User signed-in failed!.", exception.getMessage());
        }
    }

    @Override
    public BaseResponse<?> registerUser(RegisterRequest registerRequest) throws IOException {
        Query query = entityManager.createNativeQuery("exec spRegisterMember ?1, ?2, ?3, ?4, ?5");
        query.setParameter(1, Helpers.generatedMemberId());
        query.setParameter(2, registerRequest.getUsername());
        query.setParameter(3, registerRequest.getEmail());
        query.setParameter(4, passwordEncoder.encode(registerRequest.getPassword()));
        query.setParameter(5, compressB64(registerRequest.getPassword()));
        Object data = query.getSingleResult();
        return BaseResponse.ok(data.toString());
    }
}
