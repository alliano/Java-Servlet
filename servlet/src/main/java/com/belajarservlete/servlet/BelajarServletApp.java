package com.belajarservlete.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Digunakan untuk meng Secan semua class Servlet yang kita buat
 */
@ServletComponentScan
/*
 * Untuk menjalankan Embeded Tomcat
 */
@SpringBootApplication
public class BelajarServletApp {
    public static void main(String... args) {
        SpringApplication.run(BelajarServletApp.class, args);
    }
}
