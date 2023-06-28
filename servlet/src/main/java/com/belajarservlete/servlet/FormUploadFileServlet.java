package com.belajarservlete.servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet(urlPatterns = {"/upload"}) @MultipartConfig
public class FormUploadFileServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of(FormHtmlServlet.class.getResource("/html/form-upload.html").getPath());
        String html = Files.readString(path);
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           Part file = req.getPart("file");
           Path fileLocation = Path.of("upload/"+ UUID.randomUUID().toString()+file.getSubmittedFileName());
           Files.copy(file.getInputStream(), fileLocation);
           resp.getWriter().println(fileLocation.toString());
        } catch (IOException IOX) {
            resp.getWriter().println(IOX.getMessage());

       }
    }
}
