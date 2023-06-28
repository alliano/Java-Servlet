package com.belajarservlete.servlet;

import java.io.IOException;
import java.util.stream.Stream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/query"})
public class QueryServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] numbers = req.getParameterValues("number");
        resp.getWriter().println("Total Number is "+Stream.of(numbers).mapToInt(Integer::parseInt).sum());
    }
}
