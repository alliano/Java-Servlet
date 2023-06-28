package com.belajarservlete.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/json"})
public class ResponseHeaderServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dataJson = """
                {
                    "name" : "Alliano",
                    "email" : "allianoanoanymous@gmail.com",
                    "address" : {
                        "country" : "Indonesia", 
                        "stret" : "Mawar", 
                        "province" : "Bandung"
                    }
                }
                """;
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(dataJson);
    }
}
