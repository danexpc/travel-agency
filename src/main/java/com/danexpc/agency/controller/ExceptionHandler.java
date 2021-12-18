package com.danexpc.agency.controller;

import com.danexpc.agency.dto.response.ErrorResponseDto;
import com.danexpc.agency.exceptions.DaoException;
import com.danexpc.agency.exceptions.EntityAlreadyExistsException;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.exceptions.InvalidRequestDtoException;
import com.danexpc.agency.exceptions.UniqueViolationException;
import com.danexpc.agency.exceptions.UnprocessableEntityException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@Slf4j
@WebServlet(name = "ExceptionHandler", value = "/exceptions-handler", loadOnStartup = 1)
public class ExceptionHandler extends HttpServlet {

    private final int UNPROCESSABLE_ENTITY_EXCEPTION = 422;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        doHandleException(request, response);
    }

    @Override
    protected void doPost (HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        doHandleException(request, response);
    }

    @Override
    protected void doPut (HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {
        doHandleException(request, response);
    }

    private void doHandleException(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        Object cause = request.getAttribute("javax.servlet.error.exception_type");
        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");

        log.error(exception.getMessage());
        log.error(Arrays.toString(exception.getStackTrace()));

        if (cause.equals(InvalidFormatException.class)) {
            doHandleInvalidFormatException(response);
        } else if (cause.equals(EntityNotFoundException.class)) {
            doHandleEntityNotFoundException(response);
        } else if (cause.equals(UniqueViolationException.class)) {
            doHandleUniqueViolationException(response);
        } else if (cause.equals(UnprocessableEntityException.class)) {
            doHandleUnprocessableEntityException(request, response);
        } else if (cause.equals(UnrecognizedPropertyException.class)){
            doHandleUnrecognizedPropertyException(response);
        } else if (cause.equals(EntityAlreadyExistsException.class)){
            doHandleEntityAlreadyExistsException(response);
        } else if (cause.equals(DaoException.class)){
            doHandleDaoException(response);
        } else if (cause.equals(InvalidRequestDtoException.class)) {
            doHandleInvalidRequestDtoException(request, response);
        }
    }

    private void doHandleInvalidFormatException(HttpServletResponse response) throws IOException {
        writeErrorJsonToResponse("Invalid request body", HttpServletResponse.SC_BAD_REQUEST, response);
    }

    private void doHandleEntityNotFoundException(HttpServletResponse response) throws IOException {
        writeErrorJsonToResponse("Requested entity not found", HttpServletResponse.SC_NOT_FOUND, response);
    }

    private void doHandleUniqueViolationException(HttpServletResponse response) throws IOException {
        writeErrorJsonToResponse("Entity is not unique", UNPROCESSABLE_ENTITY_EXCEPTION, response);
    }

    private void doHandleUnprocessableEntityException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        writeErrorJsonToResponse(exception.getMessage(), UNPROCESSABLE_ENTITY_EXCEPTION, response);
    }

    private void doHandleUnrecognizedPropertyException(HttpServletResponse response) throws IOException {
        writeErrorJsonToResponse("Invalid request body", HttpServletResponse.SC_BAD_REQUEST, response);
    }

    private void doHandleEntityAlreadyExistsException(HttpServletResponse response) throws IOException {
        writeErrorJsonToResponse("Entity already exists", UNPROCESSABLE_ENTITY_EXCEPTION, response);
    }

    private void doHandleDaoException(HttpServletResponse response) throws IOException {
        writeErrorJsonToResponse("Unable to process request", UNPROCESSABLE_ENTITY_EXCEPTION, response);
    }

    private void doHandleInvalidRequestDtoException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        writeErrorJsonToResponse(exception.getMessage(), HttpServletResponse.SC_BAD_REQUEST, response);
    }

    private String generateErrorResponse(String message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(new ErrorResponseDto(message));
    }

    private void writeErrorJsonToResponse(String message, Integer status, HttpServletResponse response) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.print(generateErrorResponse(message));
        out.flush();
    }
}
