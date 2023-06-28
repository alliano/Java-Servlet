package com.belajarservlete.servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/download"})
public class DownloadFileServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Path.of("upload/".concat(req.getParameter("file")));
        byte[] fileByts = Files.readAllBytes(path);

        String force = req.getParameter("force");
        if ("true".equals(force)) {
            // jika kita ingin langsung download dan tidak mau melihat filenya pada browser, kita bisa set headernya seperti ini
            resp.setHeader("Content-Disposition", "Attachment; FileName=\"" + path.getFileName() + "\"");
        }
        // getOutputStream() digunakan untuk menampilkan file yang didownload di browser
        resp.getOutputStream().write(fileByts);
    }
}
