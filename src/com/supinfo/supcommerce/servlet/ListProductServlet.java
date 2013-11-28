package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.entity.Product;

/**
 * <b>ListProductServlet</b>
 * <p>
 * List all products registered in memory (through SupCommerce.jar)
 * </p>
 * 
 * @author Elka
 * @version 1.1
 * @since SupCommerce 2.1
 */
@WebServlet(displayName = "ListProduct", description = "Servlet to List all registered products", urlPatterns = "/listProduct")
public class ListProductServlet extends HttpServlet {
	private static final long		serialVersionUID	= 1L;
	
	private static final String		ATT_PRODUCTS_REQ	= "products";
	
	private static final String		ALL_PRODUCTS_QUERY	= "SELECT p FROM Product p";
	
	private static final String		LIST_PRODUCT_VIEW	= "/WEB-INF/layout/listProduct.jsp";
	
	private EntityManagerFactory	emf;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListProductServlet() {
		super();
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
		// Instanciate EntityManagerFactory, only once
		emf = Persistence.createEntityManagerFactory("supcommerce-pu");
	}
	
	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		// Close EMF
		emf.close();
		super.destroy();
	}
	
	/**
	 * Handle all HTTP methods (GET, POST, PUT, DELETE, ...).
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		// Retrieve Product list
		final List<Product> products = this.getAllProducts();
		
		// Place product list in request paramters
		request.setAttribute(ATT_PRODUCTS_REQ, products);
		
		// Forward to product list view
		request.getRequestDispatcher(LIST_PRODUCT_VIEW).forward(request, response);
	}
	
	/**
	 * Retrieve all products
	 * 
	 * @return all products from database.
	 */
	private List<Product> getAllProducts() {
		final EntityManager em = emf.createEntityManager();
		List<Product> products = new ArrayList<Product>();
		
		try {
			TypedQuery<Product> query = em.createQuery(ALL_PRODUCTS_QUERY, Product.class);
			// Inference Type
			products = query.getResultList();
		} finally {
			em.close();
		}
		
		return products;
	}
}
