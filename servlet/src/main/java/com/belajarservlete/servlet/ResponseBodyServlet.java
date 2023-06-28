package com.belajarservlete.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/body"})
public class ResponseBodyServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = """
                <body>
                    <html>
                        <h1>
                            Hello Wolrd !
                        </h1>
                    </html>
                </body>
                """;
        resp.getWriter().println(html);
    }
}
