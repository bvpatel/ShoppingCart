package com.equalexperts.shoppingcart;

public class Offer {
    private  String name;
    private int buyQuantity;
    private int freeQuantity;
    private Product freeProduct;

    public Offer(String name, int buyQuantity, int freeQuantity, Product freeProduct) {
        this.name = name;
        this.buyQuantity = buyQuantity;
        this.freeQuantity = freeQuantity;
        this.freeProduct = freeProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuyQuntity() {
        return buyQuantity;
    }

    public void setBuyQuntity(int buyQuntity) {
        this.buyQuantity = buyQuntity;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }

    public void setFreeQuantity(int freeQuantity) {
        this.freeQuantity = freeQuantity;
    }

    public Product getFreeProduct() {
        return freeProduct;
    }

    public void setFreeProduct(Product freeProduct) {
        this.freeProduct = freeProduct;
    }


}
