package com.nestandrest.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/usermanagement-list")
public class UserListController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to userList.jsp for frontend display
    	request.getRequestDispatcher("/WEB-INF/pages/user-management/usermanagement-list.jsp").forward(request, response);

    }
}

