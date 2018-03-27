package com.equalexperts.shoppingcart.exception;

/**
 * Throw this exception to indicate invalid operation on product which does not belong to a shopping cart  
 * 
 */
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 43L;
	
	public ProductNotFoundException(String message) {
		super(message);
	}

}