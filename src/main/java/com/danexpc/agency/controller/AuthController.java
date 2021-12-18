package com.danexpc.agency.controller;

import com.danexpc.agency.dto.request.UserAuthRequest;
import com.danexpc.agency.dto.response.UserLoginResponse;
import com.danexpc.agency.security.JWTUtil;
import com.danexpc.agency.service.ApiRequestValidationService;
import com.danexpc.agency.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import static com.danexpc.agency.filter.RequestValidationFilter.parseJwt;

@WebServlet(name = "AuthController", value = "/auth/*")
public class AuthController extends HttpServlet {

    private final AuthService authService = new AuthService();
    private final ApiRequestValidationService apiRequestValidationService = new ApiRequestValidationService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/login")) {
            doLoginUser(request, response);
        } else if (uri.matches(".*/register")) {
            doRegisterUser(request, response);
        } else if (uri.matches(".*/refresh-token")) {
            doRefreshToken(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doLoginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();
        UserAuthRequest dto = objectMapper.readValue(json, UserAuthRequest.class);
        apiRequestValidationService.validateAuthRequestDto(dto);

        UserLoginResponse responseDto = authService.login(dto);

        String jsonResponse = objectMapper.writeValueAsString(responseDto);
        out.print(jsonResponse);
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        UserLoginResponse responseDto = authService.refreshToken(JWTUtil.getUserId(parseJwt(request)));

        String jsonResponse = objectMapper.writeValueAsString(responseDto);
        out.print(jsonResponse);
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doRegisterUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();
        UserAuthRequest dto = objectMapper.readValue(json, UserAuthRequest.class);
        apiRequestValidationService.validateAuthRequestDto(dto);
        authService.register(dto);

        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
