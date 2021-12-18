package com.danexpc.agency.controller;

import com.danexpc.agency.dto.request.TourRequestDto;
import com.danexpc.agency.dto.response.TourResponseDto;
import com.danexpc.agency.helpers.Pagination;
import com.danexpc.agency.helpers.PaginationExtractor;
import com.danexpc.agency.service.ApiRequestValidationService;
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
    private final ApiRequestValidationService apiRequestValidationService = new ApiRequestValidationService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/tours")) {
            doGetTours(request, response);
        } else if (uri.matches(".*/tours/([0-9]+)(/?).*")) {
            doGetTour(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/tours")) {
            doCreateTour(request, response);
        } else if (uri.matches(".*/tours/([0-9]+)(/?).*")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/tours")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (uri.matches(".*/tours/([0-9]+)(/?).*")) {
            doUpdateTour(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/tours")) {
            response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
        } else if (uri.matches(".*/tours/([0-9]+)(/?).*")) {
            doDeleteTour(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doGetTours(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Pagination pagination = PaginationExtractor.extractPaginationObjectFromRequest(request);

        List<TourResponseDto> dtos = tourService.getAllTours(pagination);

        ObjectMapper objectMapper = new ObjectMapper();
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
            TourResponseDto dto = tourService.getTourById(Integer.valueOf(m.group(1)));

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

        TourRequestDto dto = objectMapper.readValue(json, TourRequestDto.class);
        apiRequestValidationService.validateTourRequestDto(dto);

        tourService.createTour(dto);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    public void doUpdateTour(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);

        TourRequestDto dto = objectMapper.readValue(json, TourRequestDto.class);
        apiRequestValidationService.validateTourRequestDto(dto);

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/tours/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            tourService.updateTour(Integer.valueOf(m.group(1)), dto);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void doDeleteTour(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/tours/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            tourService.deleteTourById(Integer.valueOf(m.group(1)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void destroy() {
    }
}
