package com.belajarservlete.servlet;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/header"})
public class HeaderServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Enumeration<String> names = req.getHeaderNames();
       String name;
       while((name = names.nextElement()) != null){
        String value = req.getHeader(name);
        resp.getWriter().println("Header "+req.getHeader(name)+" value "+ value);
       }
    }
}
