package com.danexpc.agency.controller;

import com.danexpc.agency.dto.request.LocationRequestDto;
import com.danexpc.agency.dto.response.LocationResponseDto;
import com.danexpc.agency.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebServlet(name = "LocationController", value = "/locations/*")
public class LocationController {
    private final LocationService locationService = new LocationService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches("/locations")) {
            doGetLocations(request, response);
        } else if (uri.matches(".*/locations/([0-9]+)(/?).*")) {
            doGetLocation(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches("/locations")) {
            doCreateLocation(request, response);
        } else if (uri.matches(".*/locations/([0-9]+)(/?).*")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches("/locations")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (uri.matches(".*/locations/([0-9]+)(/?).*")) {
            doUpdateLocation(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches("/locations")) {
            response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
        } else if (uri.matches(".*/locations/([0-9]+)(/?).*")) {
            doDeleteLocation(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doGetLocations(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        List<LocationResponseDto> dtos = locationService.getAllLocations();

        String json = objectMapper.writeValueAsString(dtos);

        out.print(json);
        out.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doGetLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/locations/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            LocationResponseDto dto = locationService.getLocationById(Integer.getInteger(m.group(1)));

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(dto);

            out.print(json);
            out.flush();
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void doCreateLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);

        LocationRequestDto dto = objectMapper.readValue(json, LocationRequestDto.class);
        System.out.println(dto);

        locationService.createLocation(dto);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    public void doUpdateLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);

        LocationRequestDto dto = objectMapper.readValue(json, LocationRequestDto.class);
        System.out.println(dto);

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/locations/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            locationService.updateLocation(Integer.getInteger(m.group(1)), dto);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void doDeleteLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/locations/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            locationService.deleteLocationById(Integer.getInteger(m.group(1)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void destroy() {
    }
}
