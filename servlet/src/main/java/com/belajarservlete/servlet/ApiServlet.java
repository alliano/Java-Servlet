package com.belajarservlete.servlet;

import java.io.IOException;
import java.util.Map;

import com.belajarservlete.servlet.models.Hello;
import com.belajarservlete.servlet.utils.JsonUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/api/hello"})
public class ApiServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hello hello = JsonUtil.getObjectMapper().readValue(req.getInputStream(), Hello.class);
        String sayHello = "Hello ".concat(hello.getFistName()+" "+hello.getLastName());
        Map<String, String> response = Map.of("data", sayHello);

        String jsonResponse = JsonUtil.getObjectMapper().writeValueAsString(response);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(jsonResponse);
    }
}
