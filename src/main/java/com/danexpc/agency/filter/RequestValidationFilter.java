package com.danexpc.agency.filter;

import com.danexpc.agency.security.JWTUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static io.jsonwebtoken.lang.Strings.hasText;

@WebFilter(urlPatterns = {"/*"})
public class RequestValidationFilter implements Filter {
    private static final String HEADER_AUTH_PREFIX = "Bearer ";
    private static final String HEADER_AUTH = "Authorization";

    private static final Set<String> ALLOWED_PATHS = Set.of("/auth/login");

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain)
            throws IOException, ServletException {

        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;

        if (ALLOWED_PATHS.contains(httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()).replaceAll("[/]+$", "" ))) {
            filterChain.doFilter(request, response);
            return;
        }

        String authTokenHeader = parseJwt(httpRequest);

        if (authTokenHeader == null || authTokenHeader.trim().isEmpty()) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (!JWTUtil.validateJwtToken(authTokenHeader)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Integer userType = JWTUtil.getUserType(authTokenHeader);

        System.out.println(userType);

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(HEADER_AUTH);

        return hasText(headerAuth) && headerAuth.startsWith(HEADER_AUTH_PREFIX)
                ? headerAuth.substring(HEADER_AUTH_PREFIX.length())
                : null;
    }
}
