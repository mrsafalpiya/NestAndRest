package com.nestandrest.controller;

import com.nestandrest.model.UserModel;
import com.nestandrest.service.UserService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/usermanagement-list")
public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    /**
     * Initializes the servlet by creating an instance of UserService.
     * This method is called once when the servlet is first loaded.
     */

    @Override
    public void init() {
        userService = new UserService();
    }
    
    /**
     * Handles GET requests to list users with pagination and optional search.
     *
     * Parameters:
     * - searchTerm (optional): Filters user list.
     * - page (optional): Current page number (default is 1).
     *
     * Forwards to: usermanagement-list.jsp with user list and pagination data.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchTerm = request.getParameter("searchTerm");
        int page = 1;
        int recordsPerPage = 5;

        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException ignored) {}
        }

        List<UserModel> users;
        int totalRecords;

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            users = userService.searchUsers(searchTerm.trim(), (page - 1) * recordsPerPage, recordsPerPage);
            totalRecords = userService.countSearchUsers(searchTerm.trim());
        } else {
            users = userService.getUsers((page - 1) * recordsPerPage, recordsPerPage);
            totalRecords = userService.getUserCount();
        }

        int totalPages = (int) Math.ceil(totalRecords / (double) recordsPerPage);

        request.setAttribute("userList", users);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchTerm", searchTerm); // Important for pagination links

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/user-management/usermanagement-list.jsp");
        rd.forward(request, response);
    }
}
