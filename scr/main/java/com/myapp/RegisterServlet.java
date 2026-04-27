package com.myapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String email     = request.getParameter("email");
        String password  = request.getParameter("password");

        // Basic validation
        if (email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            response.sendRedirect("register.html?error=missing");
            return;
        }

        // Check if already registered
        if (UserStore.exists(email)) {
            response.sendRedirect("register.html?error=exists");
            return;
        }

        // Save the user
        UserStore.register(email, password);
        System.out.println("Registered: " + firstName + " " + lastName + " | " + email);

        // Redirect to login with success message
        response.sendRedirect("login.html?registered=1");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.html");
    }
}
