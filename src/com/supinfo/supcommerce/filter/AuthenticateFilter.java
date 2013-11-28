package com.supinfo.supcommerce.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>AuthenticateFilter</b>
 * <p>
 * Check authentication
 * 
 * @author Elka
 * @version 1.0
 * @since SupCommerce 2.2
 */
@WebFilter(displayName = "Authenticate", description = "Filter to check authentication", urlPatterns = "/auth/*")
public class AuthenticateFilter implements Filter {
	
	private static final String	USERNAME_SESSION_ATT	= "username";
	private static final String	LOGIN_SERVLET			= "/login";
	
	/**
	 * Default constructor.
	 */
	public AuthenticateFilter() {
	}
	
	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	}
	
	/**
	 * Filter requested url bind on /auth/*...
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// Retrieve "username" session attribute
		final Object usernameAtt = httpRequest.getSession().getAttribute(USERNAME_SESSION_ATT);
		
		// Authenticated
		if (usernameAtt != null && usernameAtt instanceof String)
			// Redirect to next requested chain element
			chain.doFilter(request, response);
		else
			// Redirect to login page
			httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_SERVLET);
		
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// Do nothing
	}
	
}
