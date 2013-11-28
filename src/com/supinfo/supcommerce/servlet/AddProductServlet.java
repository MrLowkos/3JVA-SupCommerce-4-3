package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class AddProductServlet Register product with specifics settings in memory (via
 * SupCommerce.jar)
 * 
 * @author Elka
 * @version 1.0
 * @since SupCommerce 3.1
 */
@WebServlet(description = "Servlet To Add A Specific Product", urlPatterns = { "/auth/addProduct" })
public class AddProductServlet extends HttpServlet {
	private static final long		serialVersionUID		= 1L;
	
	private static final String		ID_GET_PARAM			= "id";
	
	private static final String		PRODUCT_NAME_PARAM		= "product-name";
	private static final String		PRODUCT_CONTENT_PARAM	= "product-content";
	private static final String		PRODUCT_PRICE_PARAM		= "product-price";
	private static final String		CATEGORY_ID_PARAM		= "category-id";
	
	private static final String		NAME_REQ_ATT			= "name";
	private static final String		CONTENT_REQ_ATT			= "content";
	private static final String		PRICE_REQ_ATT			= "price";
	private static final String		CATEGORY_ID_REQ_ATT		= "categoryId";
	private static final String		CATEGORIES_REQ_ATT		= "categories";
	private static final String		ERROR_NAME_REQ_ATT		= "nameError";
	private static final String		ERROR_CONTENT_REQ_ATT	= "contentError";
	private static final String		ERROR_PRICE_REQ_ATT		= "priceError";
	private static final String		ERROR_CATEGORY_REQ_ATT	= "categoryError";
	
	private static final String		CATEGORIES_QUERY_STRING	= "SELECT c FROM Category c";
	
	private static final String		SHOW_PRODUCT_SERVLET	= "/showProduct";
	private static final String		ADD_PRODUCT_VIEW		= "/WEB-INF/layout/addProduct.jsp";
	
	private EntityManagerFactory	emf;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductServlet() {
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
	 * Handles <code>GET</code> HTTP method Forward to appropriate view
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
		// Load all categories to select a category in form
		final List<Category> allCategories = this.getAllCategories();
		
		request.setAttribute(CATEGORIES_REQ_ATT, allCategories);
		// Forward to add product form
		request.getRequestDispatcher(ADD_PRODUCT_VIEW).forward(request, response);
	}
	
	/**
	 * Handles <code>POST</code> HTTP method Process product creation form
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
		
		String nameError = "";
		String contentError = "";
		String priceError = "";
		String categoryError = "";
		
		// Retrieve POST parameters
		final Object name = request.getParameter(PRODUCT_NAME_PARAM);
		final Object content = request.getParameter(PRODUCT_CONTENT_PARAM);
		final Object price = request.getParameter(PRODUCT_PRICE_PARAM);
		final Object categoryId = request.getParameter(CATEGORY_ID_PARAM);
		
		// New empty product
		final Product product = new Product();
		
		// Check data integrity (Never Trust User Input)
		// - product name
		if (name != null && name instanceof String)
			if (name != "")
				product.setName((String) name);
			else
				nameError = "Empty flied !";
		else
			nameError = "Invalid name !";
		
		// - product content
		if (content != null && content instanceof String)
			if (content != "")
				product.setContent((String) content);
			else
				contentError = "Empty flied !";
		else
			contentError = "Invalid description !";
		
		// - product price (Float)
		if (price != null && price instanceof String) {
			if (price != "") {
				try {
					product.setPrice(Float.parseFloat((String) price));
				} catch (NullPointerException e) {
					priceError = "Invalid price !";
				} catch (NumberFormatException e) {
					priceError = "Not a float number !";
				}
			} else {
				priceError = "Empty flied !";
			}
		} else
			priceError = "Invalid price !";
		
		// - product category
		if (categoryId != null && price instanceof String) {
			if (categoryId != "") {
				final Long categoryIdLong = Long.valueOf((String) categoryId);
				final Category category = findCategoryById(categoryIdLong);
				product.setCategory(category);
			} else {
				categoryError = "No category selected !";
			}
		} else
			categoryError = "Invalid category !";
		
		// Everithing is ok
		if (nameError == "" && contentError == "" && priceError == "" && categoryError == "") {
			// Add product in datebase
			this.addProduct(product);
			
			// Redirection to /showProduct?id=X
			response.sendRedirect(request.getServletContext().getContextPath() + SHOW_PRODUCT_SERVLET + "?"
					+ ID_GET_PARAM + "=" + product.getId());
		}
		// Invalid form completion - Set request attributes and forward to addProduct.jsp
		else {
			// Save correct parameters and set errors
			if (nameError != "")
				request.setAttribute(ERROR_NAME_REQ_ATT, nameError);
			else
				request.setAttribute(NAME_REQ_ATT, name);
			
			if (contentError != "")
				request.setAttribute(ERROR_CONTENT_REQ_ATT, contentError);
			else
				request.setAttribute(CONTENT_REQ_ATT, content);
			
			if (priceError != "")
				request.setAttribute(ERROR_PRICE_REQ_ATT, priceError);
			else
				request.setAttribute(PRICE_REQ_ATT, price);
			
			String id = (String) categoryId;
			if (categoryError != "")
				request.setAttribute(ERROR_CATEGORY_REQ_ATT, categoryError);
			else
				request.setAttribute(CATEGORY_ID_REQ_ATT, id);
			
			// Return to category form
			this.doGet(request, response);
		}
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
			// Inference Type !
			categories = query.getResultList();
		} finally {
			em.close();
		}
		
		return categories;
	}
	
	/**
	 * Retrieve category by id (PrimaryKey).
	 * 
	 * @param categoryId
	 *            Category id
	 * @return Category matching with categoryId
	 */
	private Category findCategoryById(Long categoryId) {
		final EntityManager em = emf.createEntityManager();
		try {
			return em.find(Category.class, categoryId);
		} finally {
			em.close();
		}
	}
}
