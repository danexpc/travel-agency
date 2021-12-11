package com.danexpc.agency.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

        if (cause.equals(InvalidFormatException.class)) {
            doHandleInvalidFormatException(response);
        } else if (cause.equals(EntityNotFoundException.class)) {
            doHandleInvalidFormatException(response);
        } else if (cause.equals(UniqueViolationException.class)) {
            doHandleInvalidFormatException(response);
        } else if (cause.equals(UnprocessableEntityException.class)) {
            doHandleInvalidFormatException(response);
        } else if (cause.equals(UnrecognizedPropertyException.class)){
            doHandleInvalidFormatException(response);
        } else if (cause.equals(EntityAlreadyExistsException.class)){
            doHandleInvalidFormatException(response);
        } else if (cause.equals(DaoException.class)){
            doHandleInvalidFormatException(response);
        }
    }

    private void doHandleInvalidFormatException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = response.getWriter();

        out.print("Invalid request body");
        out.flush();
    }
}
