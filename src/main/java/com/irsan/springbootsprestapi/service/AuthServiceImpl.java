package com.irsan.springbootsprestapi.service;

import com.irsan.springbootsprestapi.model.LoginRequest;
import com.irsan.springbootsprestapi.model.MemberPerpusData;
import com.irsan.springbootsprestapi.model.RegisterRequest;
import com.irsan.springbootsprestapi.utils.BaseResponse;
import com.irsan.springbootsprestapi.utils.DBHandler;
import com.irsan.springbootsprestapi.utils.JwtTokenUtil;
import com.irsan.springbootsprestapi.utils.SessionUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

import static com.irsan.springbootsprestapi.utils.CompressionUtil.compressB64;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Service
public class AuthServiceImpl extends DBHandler implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public BaseResponse<?> authenticateUser(LoginRequest loginRequest) {
        getStringBaseResponse(loginRequest);
        Object[] params = {loginRequest.getUsernameOrEmail()};
        HashMap<String, String> data = getSingleResult("spMemberPerpusDetailLihat", params);
        MemberPerpusData memberPerpusData = MemberPerpusData.builder()
                .memberId(data.get("member_id"))
                .namaLengkap(data.get("nama_lengkap"))
                .username(data.get("username"))
                .email(data.get("email"))
                .ttl(data.get("ttl"))
                .nik(data.get("nik"))
                .alamatDomisili(data.get("alamat_domisili"))
                .nomorHandphone(data.get("nomor_handphone"))
                .namaRole(data.get("nama_role"))
                .isAktif(data.get("is_aktif"))
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
        Object[] params = {registerRequest.getUsername(), registerRequest.getEmail(), passwordEncoder.encode(registerRequest.getPassword()), compressB64(registerRequest.getPassword())};
        HashMap<String, String> data = getSingleResult("spRegisterMember", params);
        return BaseResponse.ok(data.get("data"));
    }

    @Override
    public BaseResponse<?> checkProfile(HttpServletRequest request) {
        MemberPerpusData memberPerpusData = SessionUtil.getMemberPerpusData(request);
        return BaseResponse.ok(memberPerpusData);
    }
}
