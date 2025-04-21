package com.nestandrest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.nestandrest.util.CookiesUtil;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  // Invalidate the session to log out
        req.getSession().invalidate();
        
        CookiesUtil.deleteCookie(resp, "email");
        CookiesUtil.deleteCookie(resp, "name");

        // Redirect to login page
        //resp.sendRedirect(req.getContextPath() + "/login");
        resp.sendRedirect(req.getContextPath() + "/login?logout=success");
	}

	

}
