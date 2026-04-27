package com.myapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // "username" field in login.html holds the email address
        String email    = request.getParameter("username");
        String password = request.getParameter("password");

        if (UserStore.isValid(email, password)) {
            // Valid — create session and go to dashboard
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            response.sendRedirect("dashboard.jsp");
        } else {
            // Invalid — back to login with error flag
            response.sendRedirect("login.html?error=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.html");
    }
}
