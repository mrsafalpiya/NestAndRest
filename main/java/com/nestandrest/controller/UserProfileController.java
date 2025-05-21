package com.nestandrest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.nestandrest.model.UserModel;
import com.nestandrest.service.RegistrationService;
import com.nestandrest.service.UserService;
import com.nestandrest.util.CookiesUtil;
import com.nestandrest.util.ImageUtil;
import com.nestandrest.util.PasswordUtil;
import com.nestandrest.util.RedirectionUtil;
import com.nestandrest.util.ValidationUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet(asyncSupported = true, urlPatterns = { "/userprofile" })
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private RegistrationService registrationService;
	private RedirectionUtil redirectionUtil;
	private ValidationUtil validationUtil;
	private ImageUtil imageUtil;

	@Override
	public void init() throws ServletException {
		this.userService = new UserService();
		this.registrationService = new RegistrationService();
		this.redirectionUtil = new RedirectionUtil();
		this.validationUtil = new ValidationUtil();
		this.imageUtil = new ImageUtil();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("user", this.userService.getCurrentlyLoggedInUser(request, null));
		request.setAttribute("genders", registrationService.getGenders());

		request.getRequestDispatcher("/WEB-INF/pages/user/user-profile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String updateType = req.getParameter("update");

		UserModel currentUser = this.userService.getCurrentlyLoggedInUser(req, null);

		req.setAttribute("user", this.userService.getCurrentlyLoggedInUser(req, null));
		req.setAttribute("genders", registrationService.getGenders());

		switch (updateType.toLowerCase()) {
		case "profile": {
			UserModel updatedUser = this.extractUpdateProfileUserModel(req, resp, currentUser);
			if (updatedUser == null) {
				break;
			}
			this.userService.updateUser(updatedUser);

			// Update email in the cookie with the updated email address
			CookiesUtil.addCookie(resp, "email", updatedUser.getEmail(), 60 * 60);

			// Re-fetch current user details
			req.setAttribute("user", this.userService.getCurrentlyLoggedInUser(req, updatedUser.getEmail()));
			redirectionUtil.setMsgAndRedirect(req, resp, "success", "Profile updated successfully!",
					RedirectionUtil.editUserProfileUrl);
		}
			break;
		case "password": {
			UserModel updatedUser = this.extractUpdatePasswordUserModel(req, resp, currentUser);
			if (updatedUser == null) {
				break;
			}
			this.userService.updateUserPassword(updatedUser);

			redirectionUtil.setMsgAndRedirect(req, resp, "success", "Password updated successfully!",
					RedirectionUtil.editUserProfileUrl);
		}
		case "picture": {
			Part image = req.getPart("image");
			imageUtil.uploadImage(image, "user-images", currentUser.getUserId());

			redirectionUtil.setMsgAndRedirect(req, resp, "success", "Profile picture updated successfully!",
					RedirectionUtil.editUserProfileUrl);
		}
			break;
		}
	}

	private UserModel extractUpdateProfileUserModel(HttpServletRequest req, HttpServletResponse resp,
			UserModel currentUser) throws ServletException, IOException {
		String newFirstName = req.getParameter("first_name");
		String newLastName = req.getParameter("last_name");
		String newEmail = req.getParameter("email");
		String newPhone = req.getParameter("phone");
		int newGenderId = Integer.parseInt(req.getParameter("gender"));

		// Checking if all fields are filled
		if (validationUtil.isNullOrEmpty(newFirstName) || validationUtil.isNullOrEmpty(newLastName)
				|| validationUtil.isNullOrEmpty(newEmail) || validationUtil.isNullOrEmpty(newPhone)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "All fields are required to be filled!",
					RedirectionUtil.editUserProfileUrl);
			return null;
		}

		// Checking if a proper first and last name is provided
		if (!validationUtil.isAlphabetic(newFirstName) || !validationUtil.isAlphabetic(newLastName)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter valid name!",
					RedirectionUtil.editUserProfileUrl);
			return null;
		}

		// Checking if a valid email was provided
		if (!validationUtil.isValidEmail(newEmail)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter a valid email address!",
					RedirectionUtil.editUserProfileUrl);
			return null;
		}

		// Checking if a valid phone number was provided
		if (!validationUtil.isValidPhoneNumber(newPhone)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error",
					"Please enter a valid phone number! (Start with 98 and of 10 digits)",
					RedirectionUtil.editUserProfileUrl);
			return null;
		}

		// Check if a user with the given email address already exists (if an actual new
		// email is provided)
		if (!currentUser.getEmail().equalsIgnoreCase(newEmail)
				&& registrationService.doesAUserWithEmailExist(newEmail)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "User with the given email address already exists!",
					RedirectionUtil.editUserProfileUrl);
			return null;
		}

		// Check if a user with the given phone number already exists (if an actual new
		// phone is provided)
		if (!currentUser.getPhone().equalsIgnoreCase(newPhone)
				&& registrationService.doesAUserWithPhoneNumberExist(newPhone)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "User with the given phone number already exists!",
					RedirectionUtil.editUserProfileUrl);
			return null;
		}

		UserModel user = new UserModel(newFirstName.concat(" ").concat(newLastName), newEmail, newPhone, newGenderId,
				null, currentUser.getRoleId());
		user.setUserId(currentUser.getUserId());

		return user;
	}

	private UserModel extractUpdatePasswordUserModel(HttpServletRequest req, HttpServletResponse resp,
			UserModel currentUser) throws ServletException, IOException {
		String newPassword = req.getParameter("password");
		String newPasswordConfirm = req.getParameter("password-confirm");

		// Checking if passwords are matched
		if (!newPassword.equals(newPasswordConfirm)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Passwords do not match!",
					RedirectionUtil.editUserProfileUrl);
			return null;
		}

		// Checking if given password is a strong password
		if (!validationUtil.isValidPassword(newPassword)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error",
					"Password must be at least 8 characters long and include at least one uppercase letter, one number, and one special character (@$!%*?&)!",
					RedirectionUtil.editUserProfileUrl);
			return null;
		}

		// Encrypt the password
		newPassword = PasswordUtil.encrypt(currentUser.getEmail(), newPassword);

		currentUser.setPassword(newPassword);

		return currentUser;
	}
}
