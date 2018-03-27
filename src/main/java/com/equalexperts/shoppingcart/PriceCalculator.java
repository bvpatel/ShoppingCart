package com.equalexperts.shoppingcart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class PriceCalculator {

    private ShoppingCart cart;
    private double taxRate;

    public PriceCalculator(ShoppingCart cart) {
        this.cart = cart;
    }

    /**
     * This method is used to get current tax percentage.
     *
     * @return double Current tax percentage
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * This method is used to set tax rate.
     *
     * @param taxRate Tax rate percentage
     */
    public void setTaxRate(double taxRate) {
        assert taxRate >= 0 : "Tax rate should be greater than or equal to 0.";
        this.taxRate = taxRate;
        System.out.println("Tax rate set as " + taxRate + "%.");
    }

    /**
     * This method is used to get the shopping cart’s total value.
     *
     * @return double Total value of the shopping cart
     */
    public double getTotalCartValue() {
        double totalCartValue = 0;
        for(Map.Entry<Product, Integer> entry: cart.getProducts().entrySet()){
            totalCartValue += entry.getKey().getPrice() * entry.getValue();
        }
        return formatPrice(totalCartValue);
    }

    /**
     * This method is used to get total tax amount on the total cart's value.
     *
     * @return double Total tax amount
     */
    public double getTotalTax() {
        return formatPrice(((getTotalCartValue() - getDiscount())* getTaxRate()) / 100);
    }

    /**
     * This method is used to get the shopping cart's total price including taxes.
     *
     * @return double The shopping cart's total price including tax amount
     */
    public double getTotalPrice() {
        return formatPrice((getTotalCartValue() - getDiscount()) + getTotalTax());
    }

    /**
     * This method is used to formatted the value to 2 decimal places
     *
     * @param value The value which you want to format
     * @return double The formatted value
     */
    private double formatPrice(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     *
     */
    public double getDiscount() {
        double discout = 0;
        for(Map.Entry<Product, Integer> entry: cart.getProducts().entrySet()){
            Offer offer = entry.getKey().getOffer();
            if(offer!=null){
                discout += (entry.getValue() / (offer.getBuyQuntity()+ offer.getFreeQuantity())) * offer.getFreeQuantity() * offer.getFreeProduct().getPrice();
            }
        }
        return formatPrice(discout);
    }

}
