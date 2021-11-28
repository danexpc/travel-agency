package com.danexpc.agency.controller;

import com.danexpc.agency.dto.HotelRequestDto;
import com.danexpc.agency.dto.HotelResponseDto;
import com.danexpc.agency.service.HotelService;
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

@WebServlet(name = "HotelController", value = "/hotels/*")
public class HotelController {
    private final HotelService hotelService = new HotelService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void doGetLocations(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();

        List<HotelResponseDto> dtos = hotelService.getAllHotels();

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

        Matcher m = Pattern.compile(".*/hotels/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            HotelResponseDto dto = hotelService.getHotelById(Integer.getInteger(m.group(1)));

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

        HotelRequestDto dto = objectMapper.readValue(json, HotelRequestDto.class);
        System.out.println(dto);

        hotelService.createHotel(dto);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    public void doUpdateLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);

        HotelRequestDto dto = objectMapper.readValue(json, HotelRequestDto.class);
        System.out.println(dto);

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/hotels/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            hotelService.updateHotel(Integer.getInteger(m.group(1)), dto);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void doDeleteLocation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/hotels/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            hotelService.deleteHotelById(Integer.getInteger(m.group(1)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void destroy() {
    }
}
