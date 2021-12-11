package com.danexpc.agency.controller;

import com.danexpc.agency.dto.request.UserLoginRequest;
import com.danexpc.agency.dto.response.UserLoginResponse;
import com.danexpc.agency.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "AuthController", value = "/auth/*")
public class AuthController extends HttpServlet {

    private final AuthService authService = new AuthService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/login")) {
            doLoginUser(request, response);
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
        UserLoginRequest dto = objectMapper.readValue(json, UserLoginRequest.class);
        UserLoginResponse responseDto = authService.login(dto);

        String jsonResponse = objectMapper.writeValueAsString(responseDto);
        out.print(jsonResponse);
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
