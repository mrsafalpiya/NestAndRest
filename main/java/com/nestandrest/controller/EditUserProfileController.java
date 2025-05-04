package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.model.UserModel;
import com.nestandrest.model.UserAddressModel;
import com.nestandrest.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit-user-profile")
public class EditUserProfileController extends HttpServlet {

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
            UserAddressModel address = userService.getAddressByUserId(userId);

            if (user == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                return;
            }

            request.setAttribute("user", user);
            request.setAttribute("address", address);
            request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                    response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

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
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp")
                        .forward(request, response);
            }
            return;
        }

        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            if (name == null || name.isEmpty()) {
                request.setAttribute("error", "Name is required");
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
                return;
            }

            if (email == null || email.isEmpty()) {
                request.setAttribute("error", "Email is required");
                request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
                        response);
                return;
            }

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

            UserModel user = new UserModel();
            user.setUserId(userId);
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setGenderId(genderId);
            user.setRoleId(roleId);

            UserService userService = new UserService();
            boolean updated = userService.updateUser(user);

            if (address != null && !address.isEmpty()) {
                UserAddressModel addressModel = new UserAddressModel();
                addressModel.setUserId(userId);
                addressModel.setAddress(address);

                boolean addressUpdated = userService.updateUserAddress(addressModel);
                if (!addressUpdated) {
                    request.setAttribute("error", "Failed to update address. Please try again.");
                    request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp")
                            .forward(request, response);
                    return;
                }
            }

            if (updated) {
                response.sendRedirect(request.getContextPath() + "/usermanagement-list");
            } else {
                request.setAttribute("error", "Failed to update profile. Please try again.");
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
}
