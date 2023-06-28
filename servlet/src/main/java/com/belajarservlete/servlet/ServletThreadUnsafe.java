package com.belajarservlete.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(urlPatterns = {"/unsafe"})
public class ServletThreadUnsafe extends HttpServlet {
    
    private String restpose = "";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.restpose = "Hello "+req.getParameter("name");
        try {
            Thread.sleep(Long.parseLong(req.getParameter("sleep")));
        } catch (InterruptedException ITX) {
            log.error(ITX.getMessage());
        }
        resp.getWriter().println(this.restpose);
    }
}
