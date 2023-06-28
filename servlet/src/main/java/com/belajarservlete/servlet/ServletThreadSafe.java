package com.belajarservlete.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(urlPatterns = {"/safe"})
public class ServletThreadSafe extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respose = new String().concat("Hello "+req.getParameter("name"));
        try {
            Thread.sleep(Long.parseLong(req.getParameter("sleep")));
        } catch (InterruptedException ITX) {
            log.error(ITX.getMessage());
        }
        resp.getWriter().println(respose);
    }
}
