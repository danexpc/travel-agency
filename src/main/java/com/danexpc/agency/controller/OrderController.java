package com.danexpc.agency.controller;

import com.danexpc.agency.dto.OrderRequestDto;
import com.danexpc.agency.dto.OrderResponseDto;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doGetOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        List<OrderResponseDto> dtos = orderService.getAllOrders();

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
            OrderResponseDto dto = orderService.getOrderById(Integer.getInteger(m.group(1)));

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
        System.out.println(json);

        OrderRequestDto dto = objectMapper.readValue(json, OrderRequestDto.class);
        System.out.println(dto);

        orderService.createOrder(dto);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    public void doUpdateOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);

        OrderRequestDto dto = objectMapper.readValue(json, OrderRequestDto.class);
        System.out.println(dto);

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/orders/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            orderService.updateOrder(Integer.getInteger(m.group(1)), dto);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void doDeleteTour(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/orders/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            orderService.deleteOrderById(Integer.getInteger(m.group(1)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void destroy() {
    }
}