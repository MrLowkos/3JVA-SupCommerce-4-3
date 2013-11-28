package com.supinfo.supcommerce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>LogoutServlet</b>
 * <p>
 * Invalidate session
 * </p>
 * 
 * @author Elka
 * @version 1.0
 * @since SupCommerce 3.1
 */
@WebServlet(displayName = "Logout", description = "Servlet to control logout", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long	serialVersionUID		= 1L;
	
	private static final String	USERNAME_SESSION_PARAM	= "username";
	private static final String	LOGIN_SERVLET			= "/login";
	private static final String	ROOT_VIEW				= "/";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
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
		
		// Retrieve username session parameter
		final Object sessionUsername = request.getSession().getAttribute(USERNAME_SESSION_PARAM);
		
		// Username exist in session
		if (sessionUsername != null && sessionUsername instanceof String) {
			// Destroy session
			request.getSession().invalidate();
			// Redirect to welcome page
			response.sendRedirect(request.getServletContext().getContextPath() + ROOT_VIEW);
		} else
			// Redirect to login page
			response.sendRedirect(request.getServletContext().getContextPath() + LOGIN_SERVLET);
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
		doGet(request, response);
	}
	
}
