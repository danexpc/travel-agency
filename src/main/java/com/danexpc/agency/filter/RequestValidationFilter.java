package com.danexpc.agency.filter;

import com.danexpc.agency.constants.HttpMethod;
import com.danexpc.agency.constants.UserRole;
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
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.jsonwebtoken.lang.Strings.hasText;

@WebFilter(urlPatterns = {"/*"})
public class RequestValidationFilter implements Filter {
    private static final String HEADER_AUTH_PREFIX = "Bearer ";
    private static final String HEADER_AUTH = "Authorization";

    private static final Set<String> ALLOWED_PATHS = Set.of("/auth/login", "/auth/register", "/auth/refresh-token", "/tours", "/tours/([0-9]+)(/?).*", "/schedules", "/schedules/([0-9]+)(/?).*");

    private static final Map<HttpMethod, List<String>> ALLOWED_PATHS_FOR_CLIENT = Map.of(
            HttpMethod.GET, List.of("/users/([0-9]+)/orders(/?).*", "/users/([0-9]+)/info"),
            HttpMethod.POST, List.of("/orders"),
            HttpMethod.PUT, List.of(),
            HttpMethod.PATCH, List.of(),
            HttpMethod.DELETE, List.of()
    );

    private static final Map<HttpMethod, List<String>> ALLOWED_PATHS_FOR_MANAGER = Map.of(
            HttpMethod.GET, List.of(),
            HttpMethod.POST, List.of(),
            HttpMethod.PUT, List.of(),
            HttpMethod.PATCH, List.of(),
            HttpMethod.DELETE, List.of()
    );

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain)
            throws IOException, ServletException {

        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;

        if (ALLOWED_PATHS.stream().anyMatch(regex -> getRequestedUrl(httpRequest).matches(regex))) {
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

        var userType = JWTUtil.getUserType(authTokenHeader);

        if (isUserAllowedToPerformRequest(httpRequest, userType)) {
            filterChain.doFilter(request, response);
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    }

    public static String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(HEADER_AUTH);

        return hasText(headerAuth) && headerAuth.startsWith(HEADER_AUTH_PREFIX)
                ? headerAuth.substring(HEADER_AUTH_PREFIX.length())
                : null;
    }

    private String getRequestedUrl(HttpServletRequest httpRequest) {
       return httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
    }

    private Boolean isUserAllowedToPerformRequest(HttpServletRequest request, Integer userType) {
       if (UserRole.ADMINISTRATOR.getId() == userType) {
           return true;
       } else if (UserRole.MANAGER.getId() == userType) {
           return ALLOWED_PATHS_FOR_MANAGER.getOrDefault(HttpMethod.valueOf(request.getMethod()), List.of()).stream().anyMatch(regex -> getRequestedUrl(request).matches(regex));
       } else if (UserRole.CLIENT.getId() == userType) {
           return ALLOWED_PATHS_FOR_CLIENT.getOrDefault(HttpMethod.valueOf(request.getMethod()), List.of()).stream().anyMatch(regex -> getRequestedUrl(request).matches(regex));
       }

        return false;
    }
}
