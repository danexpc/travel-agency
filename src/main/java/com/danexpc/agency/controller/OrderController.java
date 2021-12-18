package com.danexpc.agency.controller;

import com.danexpc.agency.dto.request.OrderRequestDto;
import com.danexpc.agency.dto.response.OrderResponseDto;
import com.danexpc.agency.helpers.Pagination;
import com.danexpc.agency.helpers.PaginationExtractor;
import com.danexpc.agency.service.ApiRequestValidationService;
import com.danexpc.agency.service.OrderService;
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

@WebServlet(name = "OrderServlet", value = "/orders/*")
public class OrderController extends HttpServlet {
    private final OrderService orderService = new OrderService();
    private final ApiRequestValidationService apiRequestValidationService = new ApiRequestValidationService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/orders")) {
            doGetOrders(request, response);
        } else if (uri.matches(".*/orders/([0-9]+)(/?).*")) {
            doGetOrder(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/orders")) {
            doCreateOrder(request, response);
        } else if (uri.matches(".*/orders/([0-9]+)(/?).*")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/orders")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (uri.matches(".*/orders/([0-9]+)(/?).*")) {
            doUpdateOrder(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/orders")) {
            response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
        } else if (uri.matches(".*/orders/([0-9]+)(/?).*")) {
            doDeleteOrder(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doGetOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Pagination pagination = PaginationExtractor.extractPaginationObjectFromRequest(request);

        List<OrderResponseDto> dtos = orderService.getAllOrders(pagination);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dtos);

        out.print(json);
        out.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doGetOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/orders/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            OrderResponseDto dto = orderService.getOrderById(Integer.valueOf(m.group(1)));

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(dto);

            out.print(json);
            out.flush();
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void doCreateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();

        OrderRequestDto dto = objectMapper.readValue(json, OrderRequestDto.class);
        apiRequestValidationService.validateOrderRequestDto(dto);

        orderService.createOrder(dto);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    public void doUpdateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();

        OrderRequestDto dto = objectMapper.readValue(json, OrderRequestDto.class);
        apiRequestValidationService.validateOrderRequestDto(dto);

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/orders/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            orderService.updateOrder(Integer.valueOf(m.group(1)), dto);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void doDeleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/orders/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            orderService.deleteOrderById(Integer.valueOf(m.group(1)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void destroy() {
    }
}