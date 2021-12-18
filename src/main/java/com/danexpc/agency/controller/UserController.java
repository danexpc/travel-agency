package com.danexpc.agency.controller;

import com.danexpc.agency.dto.request.UserRequestDto;
import com.danexpc.agency.dto.response.UserResponseDto;
import com.danexpc.agency.helpers.Pagination;
import com.danexpc.agency.helpers.PaginationExtractor;
import com.danexpc.agency.service.ApiRequestValidationService;
import com.danexpc.agency.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebServlet(name = "UserServlet", value = "/users/*")
public class UserController extends HttpServlet {
    private final UserService userService = new UserService();
    private final ApiRequestValidationService apiRequestValidationService = new ApiRequestValidationService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/users")) {
            doGetUsers(request, response);
        } else if (uri.matches(".*/users/([0-9]+)(/?).*")) {
            doGetUser(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/users")) {
            doCreateUser(request, response);
        } else if (uri.matches(".*/users/([0-9]+)(/?).*")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/users")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (uri.matches(".*/users/([0-9]+)(/?).*")) {
            doUpdateUser(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/users")) {
            response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
        } else if (uri.matches(".*/users/([0-9]+)(/?).*")) {
            doDeleteUser(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doGetUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Pagination pagination = PaginationExtractor.extractPaginationObjectFromRequest(request);

        List<UserResponseDto> dtos = userService.getAllUsers(pagination);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dtos);

        out.print(json);
        out.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doGetUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/users/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            UserResponseDto dto = userService.getUserById(Integer.valueOf((m.group(1))));

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(dto);

            out.print(json);
            out.flush();
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void doCreateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();

        UserRequestDto dto;

        dto = objectMapper.readValue(json, UserRequestDto.class);
        apiRequestValidationService.validateUserRequestDto(dto);

        userService.createUser(dto);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    public void doUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();

        UserRequestDto dto = objectMapper.readValue(json, UserRequestDto.class);
        apiRequestValidationService.validateUserRequestDto(dto);

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/users/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            userService.updateUser(Integer.valueOf(m.group(1)), dto);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void doDeleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/users/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            userService.deleteUserById(Integer.valueOf(m.group(1)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void destroy() {
    }
}