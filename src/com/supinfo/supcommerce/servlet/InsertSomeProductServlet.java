package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommerce.entity.Category;
import com.supinfo.supcommerce.entity.Product;

/**
 * <b>InsertSomeProductServlet</b>
 * <p>
 * Register random generated product in memory (through SupCommerce.jar)
 * </p>
 * 
 * @author Elka
 * @version 1.2
 * @since SupCommerce 2.1
 */
@WebServlet(displayName = "InsertSomeProduct", description = "Servlet to insert random product", urlPatterns = "/auth/basicInsert")
public class InsertSomeProductServlet extends HttpServlet {
	private static final long		serialVersionUID		= 1L;
	
	private static final String		WARN_CATEGORY_ATT		= "newCategoryNeeded";
	private static final String		WARN_CATEGORY_MESSAGE	= "A first category is needed to create a random product";
	
	private static final String		CATEGORIES_QUERY_STRING	= "SELECT c FROM Category c";
	
	private static final String		LIST_PRODUCT_SERVLET	= "/listProduct";
	private static final String		ADD_CATEGORY_VIEW		= "/WEB-INF/layout/addCategory.jsp";
	
	private EntityManagerFactory	emf;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertSomeProductServlet() {
		super();
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// Original process
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
		
		// Original process
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
		final Random rand = new Random();
		
		// New empty product
		final Product product = new Product();
		// Set product properties randomly
		product.setName("Product-" + rand.nextInt(1000));
		product.setContent("Product information of " + product.getName() + " is unset.");
		product.setPrice(rand.nextFloat() + rand.nextInt(1000));
		
		// retrieve categories
		List<Category> categories = this.getAllCategories();
		
		// No category registered
		if (categories.isEmpty()) {
			// Add Warning
			request.setAttribute(WARN_CATEGORY_ATT, WARN_CATEGORY_MESSAGE);
			// Forwars to add Category - a first one is need
			request.getRequestDispatcher(ADD_CATEGORY_VIEW).forward(request, response);
		} else {
			// Choose a random category
			product.setCategory(categories.get(rand.nextInt(categories.size())));
			// Add in database
			this.addProduct(product);
			// Redirect to list product
			response.sendRedirect(request.getServletContext().getContextPath() + LIST_PRODUCT_SERVLET);
		}
	}
	
	/**
	 * Retrieve all categories
	 * 
	 * @return all categories from database.
	 */
	private List<Category> getAllCategories() {
		final EntityManager em = emf.createEntityManager();
		List<Category> categories = new ArrayList<Category>();
		
		try {
			TypedQuery<Category> query = em.createQuery(CATEGORIES_QUERY_STRING, Category.class);
			// Inference Type
			categories = query.getResultList();
		} finally {
			em.close();
		}
		return categories;
	}
	
	/**
	 * Add Product in database (Persistence).
	 * 
	 * @param product
	 *            Product to add.
	 */
	private void addProduct(Product product) {
		final EntityManager em = emf.createEntityManager();
		// New transaction
		final EntityTransaction t = em.getTransaction();
		
		try {
			t.begin();
			// Save in database
			em.persist(product);
			t.commit();
		} finally {
			// Transaction pending -> Rollback
			if (t.isActive())
				t.rollback();
			em.close();
		}
	}
	
}
