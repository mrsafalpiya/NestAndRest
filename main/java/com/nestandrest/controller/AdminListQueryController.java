package com.nestandrest.controller;

import java.io.IOException;

import com.nestandrest.service.QueryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/user-query" })
public class AdminListQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QueryService queryService;

	@Override
	public void init() throws ServletException {
		this.queryService = new QueryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("queries", this.queryService.getAllQueries());

		req.getRequestDispatcher("WEB-INF/pages/query-management/list.jsp").forward(req, resp);
	}
}
