package com.danexpc.agency.controller;

import com.danexpc.agency.dto.request.ScheduleRequestDto;
import com.danexpc.agency.dto.response.ScheduleResponseDto;
import com.danexpc.agency.helpers.Pagination;
import com.danexpc.agency.helpers.PaginationExtractor;
import com.danexpc.agency.service.ApiRequestValidationService;
import com.danexpc.agency.service.ScheduleService;
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

@WebServlet(name = "ScheduleServlet", value = "/schedules/*")
public class ScheduleController extends HttpServlet {
    private final ScheduleService scheduleService = new ScheduleService();
    private final ApiRequestValidationService apiRequestValidationService = new ApiRequestValidationService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/schedules")) {
            doGetSchedules(request, response);
        } else if (uri.matches(".*/schedules/([0-9]+)(/?).*")) {
            doGetSchedule(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/schedules")) {
            doCreateSchedule(request, response);
        } else if (uri.matches(".*/schedules/([0-9]+)(/?).*")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/schedules")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (uri.matches(".*/schedules/([0-9]+)(/?).*")) {
            doUpdateSchedule(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        if (uri.matches(".*/schedules")) {
            response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
        } else if (uri.matches(".*/schedules/([0-9]+)(/?).*")) {
            doDeleteSchedule(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void doGetSchedules(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Pagination pagination = PaginationExtractor.extractPaginationObjectFromRequest(request);

        List<ScheduleResponseDto> dtos = scheduleService.getAllSchedules(pagination);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dtos);

        out.print(json);
        out.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doGetSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/schedules/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            ScheduleResponseDto dto = scheduleService.getScheduleById(Integer.valueOf(m.group(1)));

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(dto);

            out.print(json);
            out.flush();
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    public void doCreateSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();

        ScheduleRequestDto dto = objectMapper.readValue(json, ScheduleRequestDto.class);
        apiRequestValidationService.validateScheduleRequestDto(dto);

        scheduleService.createSchedule(dto);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    public void doUpdateSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ObjectMapper objectMapper = new ObjectMapper();

        ScheduleRequestDto dto = objectMapper.readValue(json, ScheduleRequestDto.class);
        apiRequestValidationService.validateScheduleRequestDto(dto);

        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/schedules/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            scheduleService.updateSchedule(Integer.valueOf(m.group(1)), dto);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void doDeleteSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();

        Matcher m = Pattern.compile(".*/schedules/([0-9]+)(/?).*").matcher(uri);

        if (m.matches()) {
            scheduleService.deleteScheduleById(Integer.valueOf(m.group(1)));

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    public void destroy() {
    }
}
