package com.belajarservlete.servlet;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/counter"})
public class CounterServlet extends HttpServlet {
    
    private AtomicLong atomicLong = new AtomicLong(0);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Total counter : "+this.atomicLong.incrementAndGet());
    }
}
