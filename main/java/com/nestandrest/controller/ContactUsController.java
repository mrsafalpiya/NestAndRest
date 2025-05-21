package com.nestandrest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nestandrest.model.QueryModel;
import com.nestandrest.service.QueryService;
import com.nestandrest.util.RedirectionUtil;
import com.nestandrest.util.ValidationUtil;

/**
 * Controller servlet for handling Contact Us page requests.
 *
 * @author 23049063 Himani Chaudhary
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contact-us" })
public class ContactUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ValidationUtil validationUtil;
	private RedirectionUtil redirectionUtil;
	private QueryService queryService;

	/**
	 * Initializes the ValidationUtil, RedirectionUtil, and QueryService instances.
	 *
	 * @throws ServletException if an error occurs during servlet initialization.
	 */
	@Override
	public void init() throws ServletException {
		this.validationUtil = new ValidationUtil();
		this.redirectionUtil = new RedirectionUtil();
		this.queryService = new QueryService();
	}

	/**
	 * Handles HTTP GET requests to display the Contact Us page.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/info/contact-us.jsp").forward(req, resp);
	}

	/**
	 * Handles HTTP POST requests to process and save user queries.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @throws ServletException if an error occurs during request handling.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			QueryModel queryModel = extractQueryModel(req, resp);
			Boolean isAdded = queryService.saveQuery(queryModel);

			if (isAdded == null) {
				redirectionUtil.setMsgAndRedirect(req, resp, "error",
						"An unexpected error occurred. Please try again later!", RedirectionUtil.contactUsUrl);
				return;
			}

			redirectionUtil.setMsgAndRedirect(req, resp, "success", "Your query has been submitted successfully!",
					RedirectionUtil.contactUsUrl);
		} catch (Exception e) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error",
					"An unexpected error occurred. Please try again later!", RedirectionUtil.contactUsUrl);
			e.printStackTrace();
		}
	}

	/**
	 * Extracts and validates user input to build a QueryModel.
	 *
	 * @param req  The HttpServletRequest containing the client request.
	 * @param resp The HttpServletResponse for sending the response.
	 * @return A QueryModel object containing the validated query data.
	 * @throws ServletException if validation fails or an error occurs during
	 *                          processing.
	 * @throws IOException      if an I/O error occurs during request processing.
	 */
	private QueryModel extractQueryModel(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");

		// Checking if all fields are filled
		if (validationUtil.isNullOrEmpty(name) || validationUtil.isNullOrEmpty(email)
				|| validationUtil.isNullOrEmpty(subject) || validationUtil.isNullOrEmpty(message)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "All fields are required to be filled!",
					RedirectionUtil.contactUsUrl);
			return null;
		}

		// Checking if a proper name is provided
		if (!validationUtil.isValidName(name)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter valid name!",
					RedirectionUtil.contactUsUrl);
			return null;
		}

		// Checking if a valid email was provided
		if (!validationUtil.isValidEmail(email)) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please enter a valid email address!",
					RedirectionUtil.contactUsUrl);
			return null;
		}

		return new QueryModel(name, email, subject, message);
	}
}
