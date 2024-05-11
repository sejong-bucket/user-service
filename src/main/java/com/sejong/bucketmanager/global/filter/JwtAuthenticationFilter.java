package com.sejong.bucketmanager.global.filter;

import com.sejong.bucketmanager.domain.auth.entity.AuthToken;
import com.sejong.bucketmanager.global.util.HeaderUtil;
import com.sejong.bucketmanager.global.util.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final static String AUTHORIZATION_HEADER = "accessToken";
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String parseToken = HeaderUtil.parseBearer(req.getHeader(AUTHORIZATION_HEADER));
        if (parseToken != null) {
            AuthToken authToken = tokenProvider.convertAuthToken(parseToken);
            if (authToken.validate()) {
                SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(authToken));
            }
        }
        filterChain.doFilter(req, res);
    }
}
