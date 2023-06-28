package com.belajarservlete.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/todo"})
public class HttpMethodServlet extends HttpServlet {
    
    private List<String> datas = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(this.datas);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("todo");
        if (data != null) {
            this.datas.add(data);
            resp.getWriter().println("Success Add "+data);
        } else {
            resp.getWriter().println("parameter todo must exist!");
        }
    }
}
