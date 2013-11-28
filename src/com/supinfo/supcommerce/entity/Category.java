package com.supinfo.supcommerce.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Category Product categories
 * 
 * @author Elka
 * @version 4.2
 * @since SupCommerce 4.1
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long				id;
	private String				name;
	@OneToMany(mappedBy = "category")
	private List<Product>		produtcs;
	
	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return products
	 */
	public List<Product> getProdutcs() {
		return produtcs;
	}
	
	/**
	 * @param produtcs
	 */
	public void setProdutcs(List<Product> produtcs) {
		this.produtcs = produtcs;
	}
	
}
