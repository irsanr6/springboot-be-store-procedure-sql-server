package com.irsan.springbootsprestapi.filter;

import com.irsan.springbootsprestapi.model.MemberPerpusData;
import com.irsan.springbootsprestapi.service.MemberPerpusDetailServiceImpl;
import com.irsan.springbootsprestapi.utils.Constant;
import com.irsan.springbootsprestapi.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Resource
    private MemberPerpusDetailServiceImpl memberPerpusDetailService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String usernameOrEmail = null;
        String jwtToken = null;
        MemberPerpusData memberPerpusData = new MemberPerpusData();
        String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.replace("Bearer ", "");
            memberPerpusData = jwtTokenUtil.extractToken(jwtToken);
            try {
                usernameOrEmail = memberPerpusData.getUsername();
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        if (usernameOrEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = memberPerpusDetailService.loadUserByUsername(usernameOrEmail);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        request.setAttribute(Constant.HEADER_DATA, memberPerpusData);
        filterChain.doFilter(request, response);

    }

}
