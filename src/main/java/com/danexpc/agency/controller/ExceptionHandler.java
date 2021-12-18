package com.danexpc.agency.controller;

import com.danexpc.agency.exceptions.DaoException;
import com.danexpc.agency.exceptions.EntityAlreadyExistsException;
import com.danexpc.agency.exceptions.EntityNotFoundException;
import com.danexpc.agency.exceptions.InvalidRequestDtoException;
import com.danexpc.agency.exceptions.UniqueViolationException;
import com.danexpc.agency.exceptions.UnprocessableEntityException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet(name = "ExceptionHandler", value = "/exceptions-handler", loadOnStartup = 1)
public class ExceptionHandler extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doHandleException(request, response);
    }

    @Override
    protected void doPost (HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doHandleException(request, response);
    }

    @Override
    protected void doPut (HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        doHandleException(request, response);
    }

    private void doHandleException(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        Object cause = request.getAttribute("javax.servlet.error.exception_type");

        log.error(String.valueOf(request.getAttributeNames()));

        if (cause.equals(InvalidFormatException.class)) {
            doHandleInvalidFormatException(response);
        } else if (cause.equals(EntityNotFoundException.class)) {
            doHandleEntityNotFoundException(response);
        } else if (cause.equals(UniqueViolationException.class)) {
            doHandleUniqueViolationException(response);
        } else if (cause.equals(UnprocessableEntityException.class)) {
            doHandleUnprocessableEntityException(response);
        } else if (cause.equals(UnrecognizedPropertyException.class)){
            doHandleUnrecognizedPropertyException(response);
        } else if (cause.equals(EntityAlreadyExistsException.class)){
            doHandleEntityAlreadyExistsException(response);
        } else if (cause.equals(DaoException.class)){
            doHandleDaoException(response);
        } else if (cause.equals(InvalidRequestDtoException.class)) {
            doHandleInvalidRequestDtoException(response);
        }
    }

    private void doHandleInvalidFormatException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = response.getWriter();

        out.print("Invalid request body");
        out.flush();
    }

    private void doHandleEntityNotFoundException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = response.getWriter();

        out.print("Invalid request body");
        out.flush();
    }

    private void doHandleUniqueViolationException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = response.getWriter();

        out.print("Invalid request body");
        out.flush();
    }

    private void doHandleUnprocessableEntityException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = response.getWriter();

        out.print("Invalid request body");
        out.flush();
    }

    private void doHandleUnrecognizedPropertyException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = response.getWriter();

        out.print("Invalid request body");
        out.flush();
    }

    private void doHandleEntityAlreadyExistsException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = response.getWriter();

        out.print("Invalid request body");
        out.flush();
    }

    private void doHandleDaoException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = response.getWriter();

        out.print("Invalid request body");
        out.flush();
    }

    private void doHandleInvalidRequestDtoException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = response.getWriter();

        out.print("Invalid request dto");
        out.flush();
    }
}
