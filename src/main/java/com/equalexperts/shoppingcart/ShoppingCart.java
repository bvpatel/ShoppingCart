package com.equalexperts.shoppingcart;

import com.equalexperts.shoppingcart.exception.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@link ShoppingCart} program implements an application that
 * perform functionality related to cart like number of products in cart,
 * add new product in the cart etc.
 */
public class ShoppingCart {

    private Map<Product, Integer> products;
    private long totalQuantity;

    public ShoppingCart() {
        products = new HashMap<Product, Integer>();
        totalQuantity = 0;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }


    /**
     * This method is used to find number products in shopping cart.
     *
     * @return int the number of products in shopping cart
     */
    public int getProductCount() {
        return products.size();
    }

    /**
     * This method is used to add new product into the cart.
     *
     * @param product  The product which you want to store in cart
     * @param quantity The quantity of product
     */
    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, quantity + products.get(product));
        } else {
            products.put(product, quantity);
        }
        totalQuantity += quantity;
        System.out.println(product + " added successfully in the cart with quantity: " + quantity);
    }

    public void clearCart() {
        products.clear();
        totalQuantity = 0;
        System.out.println("Cart successfully cleared");
    }


    /**
     * This method is used to find the quantity of the product in the cart.
     *
     * @param product Product which you want to know quantity in the cart
     * @return int The quantity of the product.
     */
    public int getQuantityByProduct(Product product) {
        if (!products.containsKey(product))
            throw new ProductNotFoundException("The product " + product.getName() + " is not found in the cart.");
        return products.get(product);
    }

    /**
     * This method is used to get total quatity of all products in the shopping cart.
     *
     * @return long Total quantity in the shopping cart
     */
    public long getTotalQuantity() {
        return totalQuantity;
    }


}
