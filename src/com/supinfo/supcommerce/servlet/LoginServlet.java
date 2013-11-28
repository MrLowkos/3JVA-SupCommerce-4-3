package com.supinfo.supcommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>LoginServlet</b>
 * <p>
 * Create a new session and a "username" attribute in this one.
 * <p>
 * 
 * @author Elka
 * @version 1.1
 * @since SupCommerce 2.2
 */
@WebServlet(displayName = "Login", description = "Servlet to control login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long	serialVersionUID		= 1L;
	
	private static final String	USERNAME_POST_PARAM		= "username";
	
	private static final String	LIST_PRODUCT_SERVLET	= "/listProduct";
	private static final String	LOGIN_VIEW				= "/WEB-INF/layout/login.jsp";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}
	
	/**
	 * Handles <code>GET</code> HTTP method
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(LOGIN_VIEW).forward(request, response);
	}
	
	/**
	 * Handles <code>POST</code> HTTP method
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		// Retrieve "username" parameter form login.html form
		final String usernameParam = request.getParameter(USERNAME_POST_PARAM);
		
		// Insert it in session attribute
		request.getSession().setAttribute(USERNAME_POST_PARAM, usernameParam);
		
		// Redirect to productList
		response.sendRedirect(request.getServletContext().getContextPath() + LIST_PRODUCT_SERVLET);
	}
	
}
