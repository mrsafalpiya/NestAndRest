package com.nestandrest.controller;

import java.io.IOException;


import com.nestandrest.model.UserModel;
import com.nestandrest.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/edit-user-profile")
public class EditUserProfileController extends HttpServlet {

	/**
	 * Handles HTTP GET requests for retrieving and deleting user data. If
	 * 'action=delete' is provided, it deletes the user. Otherwise, it fetches user
	 * data and forwards to edit page.
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

			// Handle deletion from POST request
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
	 * Handles HTTP POST requests for updating or deleting user data. Updates user
	 * profile and address if valid form data is provided.
	 */
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
				request.getRequestDispatcher("/WEB-INF/pages/user-management/edit-user-profile.jsp").forward(request,
						response);
			}
			return;
		}

		// Handle update
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			

			// Basic form validation
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

			// Parse additional form values
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

			

			// Final redirect or error message
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
