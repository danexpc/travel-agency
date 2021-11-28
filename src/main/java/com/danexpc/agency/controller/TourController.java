package com.danexpc.agency.controller;

import com.danexpc.agency.dto.TourRequestDto;
import com.danexpc.agency.dto.TourResponseDto;
import com.danexpc.agency.service.TourService;
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

@WebServlet(name = "TourServlet", value = "/tours/*")
public class TourController extends HttpServlet {
    private final TourService tourService = new TourService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doGetTours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        List<TourResponseDto> dtos = tourService.getAllTours();

        String json = objectMapper.writeValueAsString(dtos);

        out.print(json);
        out.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doGetTour(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/tours/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            TourResponseDto dto = tourService.getTourById(Integer.getInteger(m.group(1)));

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(dto);

            out.print(json);
            out.flush();
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void doCreateTour(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);

        TourRequestDto dto = objectMapper.readValue(json, TourRequestDto.class);
        System.out.println(dto);

        tourService.createTour(dto);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    public void doUpdateTour(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);

        TourRequestDto dto = objectMapper.readValue(json, TourRequestDto.class);
        System.out.println(dto);

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/tours/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            tourService.updateTour(Integer.getInteger(m.group(1)), dto);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void doDeleteTour(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/tours/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            tourService.deleteTourById(Integer.getInteger(m.group(1)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void destroy() {
    }
}
