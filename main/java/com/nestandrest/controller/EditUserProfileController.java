package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.model.UserModel;
import com.nestandrest.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller Servlet to handle Edit User Profile operations including
 * displaying user data, updating profile, and deleting user.
 * 
 * @author Sanniva Shakya
 */
@WebServlet("/edit-user-profile")
public class EditUserProfileController extends HttpServlet {

    /**
     * Handles GET requests to display the edit user profile page.
     * Supports optional deletion via "action=delete".
     * 
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        String action = request.getParameter("action");

        if (userIdParam == null || userIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdParam);
            UserService userService = new UserService();

            if ("delete".equalsIgnoreCase(action)) {
                boolean deleted = userService.deleteUserById(userId);
                if (deleted) {
                    response.sendRedirect(request.getContextPath() + "/usermanagement-list");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete user");
                }
                return;
            }

            UserModel user = userService.getUserById(userId);

            if (user == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                return;
            }

            // Set profile image filename since it is not stored in DB
            user.setProfileImage(user.getUserId() + ".png");

            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                    response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }

    /**
     * Handles POST requests to update or delete the user profile.
     * Validates inputs, checks phone number format, and provides success/error
     * messages.
     * 
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // Handle delete request if action=delete
        if ("delete".equalsIgnoreCase(action)) {
            try {
                int userId = Integer.parseInt(request.getParameter("userId"));
                UserService userService = new UserService();
                boolean deleted = userService.deleteUserById(userId);

                if (deleted) {
                    response.sendRedirect(request.getContextPath() + "/usermanagement-list");
                } else {
                    request.setAttribute("error", "Failed to delete the user.");
                    request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp")
                            .forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "An unexpected error occurred while deleting user.");
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
            }
            return;
        }

        // Handle update request
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            String userIdParam = request.getParameter("userId");
            String genderIdParam = request.getParameter("genderId");
            String roleIdParam = request.getParameter("roleId");

            if (userIdParam == null || genderIdParam == null || roleIdParam == null) {
                request.setAttribute("error", "Missing required fields");
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
                return;
            }

            int userId = Integer.parseInt(userIdParam);
            int genderId = Integer.parseInt(genderIdParam);
            int roleId = Integer.parseInt(roleIdParam);

            // Validate required fields
            if (name == null || name.trim().isEmpty()) {
                request.setAttribute("error", "Name is required");
                setUserRequestAttribute(request, userId, name, email, phone, genderId, roleId);
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
                return;
            }

            if (email == null || email.trim().isEmpty()) {
                request.setAttribute("error", "Email is required");
                setUserRequestAttribute(request, userId, name, email, phone, genderId, roleId);
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
                return;
            }

            if (phone == null || phone.trim().isEmpty()) {
                request.setAttribute("error", "Phone number is required");
                setUserRequestAttribute(request, userId, name, email, phone, genderId, roleId);
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
                return;
            }

            if (!phone.matches("^[0-9]{10}$")) {
                request.setAttribute("error", "Phone number must be exactly 10 digits.");
                setUserRequestAttribute(request, userId, name, email, phone, genderId, roleId);
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
                return;
            }

            UserModel user = new UserModel();
            user.setUserId(userId);
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setGenderId(genderId);
            user.setRoleId(roleId);

            // Set profile image filename for consistency if needed
            user.setProfileImage(userId + ".png");

            UserService userService = new UserService();
            boolean updated = userService.updateUser(user);

            if (updated) {
                // Forward with updated user and success message instead of redirect
                UserModel updatedUser = userService.getUserById(userId);
                updatedUser.setProfileImage(userId + ".png");
                request.setAttribute("user", updatedUser);
                request.setAttribute("success", "Profile updated successfully.");
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
            } else {
                request.setAttribute("error", "Failed to update profile. Please try again.");
                setUserRequestAttribute(request, userId, name, email, phone, genderId, roleId);
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input data. Please check your form values.");
            request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                    response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An unexpected error occurred: " + e.toString());
            request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                    response);
        }
    }

    /**
     * Helper method to set user data into request attribute for repopulating form fields.
     * 
     * @param request HttpServletRequest
     * @param userId  int user ID
     * @param name    String user name
     * @param email   String user email
     * @param phone   String user phone
     * @param genderId int gender ID
     * @param roleId  int role ID
     */
    private void setUserRequestAttribute(HttpServletRequest request, int userId, String name, String email,
            String phone, int genderId, int roleId) {
        UserModel user = new UserModel();
        user.setUserId(userId);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGenderId(genderId);
        user.setRoleId(roleId);

        // Set profile image filename here as well to avoid null in JSP
        user.setProfileImage(userId + ".png");

        request.setAttribute("user", user);
    }
}
