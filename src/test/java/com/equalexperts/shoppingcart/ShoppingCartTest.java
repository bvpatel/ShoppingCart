package com.equalexperts.shoppingcart;

import com.equalexperts.shoppingcart.exception.ProductNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
    private ShoppingCart cart;
    private PriceCalculator priceCalculator;

    @Before
    public void setUp() throws Exception {
        //Creating Empty shopping cart before running tests.
        cart = new ShoppingCart();
        priceCalculator = new PriceCalculator(cart);

    }

    @After
    public void tearDown() {
        cart.clearCart();
    }

    @Test
    public void testCreateEmptyShoppingCart() {
        //Check for empty shopping cart.
        Assert.assertEquals(0, cart.getProductCount());
        Assert.assertEquals(0, cart.getTotalQuantity());
    }

    @Test
    public void testAddProductToShoppingCart() {
        //Given Empty cart and a product, Dove Soap with a unit price of 39.99
        Product doveSoap = new Product("Dove Soap", 39.99);

        //Adds 5 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap, 5);

        //To check the shopping cart contain 5 Dove Soaps
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(5, cart.getTotalQuantity());
        Assert.assertEquals(5, cart.getQuantityByProduct(doveSoap));

        //To check the shopping cart’s total price should equal 199.95
        Assert.assertEquals(199.95, priceCalculator.getTotalPrice(), 0.0);
    }

    @Test
    public void testAddProductToShoppingCartRoundOff() {
        //Given Empty cart and a product, Colgate Toothpaste with a unit price of 40.665
        Product colgateToothpaste = new Product("Colgate Toothpaste", 40.665);

        //Adds 3 Colgate Toothpaste to the cart
        cart.addProduct(colgateToothpaste, 3);

        //To test round off the total cart value.
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(3, cart.getTotalQuantity());
        Assert.assertEquals(3, cart.getQuantityByProduct(colgateToothpaste));
        Assert.assertEquals(122, priceCalculator.getTotalPrice(), 0.0);
    }

    @Test(expected = ProductNotFoundException.class)
    public void testGetQuantityOfNonCartItem() {
        //Given Empty cart and two products, Dove Soap with a unit price of 39.99
        //And another product, Colgate Toothpaste with a unit price of 40.665
        Product doveSoap = new Product("Dove Soap", 39.99);
        Product colgateToothpaste = new Product("Colgate Toothpaste", 40.665);

        //Adds 3 Dove Soaps to the cart
        cart.addProduct(doveSoap, 3);

        //Check quantity of dove soap and colgate toothpaste is 3 and total quantity is 6
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(3, cart.getTotalQuantity());
        Assert.assertEquals(3, cart.getQuantityByProduct(doveSoap));
        cart.getQuantityByProduct(colgateToothpaste);

    }

    @Test
    public void testClearCart() {
        //Given Empty cart and two products, Dove Soap with a unit price of 39.99
        //And another product, Colgate Toothpaste with a unit price of 40.665
        Product doveSoap = new Product("Dove Soap", 39.99);
        Product colgateToothpaste = new Product("Colgate Toothpaste", 40.665);

        //Adds 3 Dove Soaps to the cart
        cart.addProduct(doveSoap, 3);
        cart.addProduct(colgateToothpaste,3);

        //Check quantity of dove soap and colgate toothpaste is 3 and total quantity is 6
        Assert.assertEquals(2, cart.getProductCount());
        Assert.assertEquals(6, cart.getTotalQuantity());
        Assert.assertEquals(3, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(3, cart.getQuantityByProduct(colgateToothpaste));
        Assert.assertEquals(241.97, priceCalculator.getTotalPrice(), 0.0);

        //Clear cart
        cart.clearCart();

        //Checking cart is clear or not
        Assert.assertEquals(0, cart.getProductCount());
        Assert.assertEquals(0, cart.getTotalQuantity());
        Assert.assertEquals(0, priceCalculator.getTotalPrice(), 0.0);


    }

    @Test
    public void testAddProductOfSameTypeToShoppingCart() {
        //Given Empty cart and a product, Dove Soap with a unit price of 39.99
        Product doveSoap = new Product("Dove Soap", 39.99);

        //Adds 5 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap, 5);

        //To check the shopping cart contain 5 Dove Soaps and the shopping cart’s total price should equal 199.95
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(5, cart.getTotalQuantity());
        Assert.assertEquals(5, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(199.95, priceCalculator.getTotalPrice(), 0.0);

        //Adds again 3 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap, 3);

        //To check the shopping cart contain 8 Dove Soaps and the shopping cart’s total price should equal 319.92
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(8, cart.getTotalQuantity());
        Assert.assertEquals(8, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(319.92, priceCalculator.getTotalPrice(), 0.0);

        //Adds again 3 Dove Soaps to the shopping cart
        doveSoap = new Product("Dove Soap", 39.99);
        cart.addProduct(doveSoap, 3);

        //To check the shopping cart contain 11 Dove Soaps and the shopping cart’s total price should equal 439.89
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(11, cart.getTotalQuantity());
        Assert.assertEquals(11, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(439.89, priceCalculator.getTotalPrice(), 0.0);
    }

    @Test(expected = AssertionError.class)
    public void testAddProductWithNegativePriceToShoppingCart() {
        //Given Empty cart and a product, Dove Soap with a unit price of -10
        Product doveSoap = new Product("Dove Soap", -10);
        cart.addProduct(doveSoap, 5);
    }

    @Test
    public void testAddTwoProductsToShoppingCart() {
        //Empty cart and two products. Dove Soap with a unit price of 39.99
        //And another product, Axe Deo with a unit price of 99.99
        Product doveSoap = new Product("Dove Soap", 39.99);
        Product axeDeo = new Product("Axe Deo", 99.99);

        //Adds 2 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap, 2);

        //Checking the shopping cart contain 2 dove soaps and the shopping cart's total price should be equal to 79.98
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(2, cart.getTotalQuantity());
        Assert.assertEquals(2, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(79.98, priceCalculator.getTotalPrice(), 0.0);

        //Adds 3 more dove soaps
        cart.addProduct(doveSoap, 3);

        //Checking the shopping cart contain 5 dove soaps and the shopping cart's total price should be equal to 199.95
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(5, cart.getTotalQuantity());
        Assert.assertEquals(5, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(199.95, priceCalculator.getTotalPrice(), 0.0);

        //Now Adds 2 Axe deo
        cart.addProduct(axeDeo, 2);

        //Checking the shopping cart contain 5 dove soaps and 2 axe deo
        //The shopping cart's total price should be equal to 199.95
        Assert.assertEquals(2, cart.getProductCount());
        Assert.assertEquals(7, cart.getTotalQuantity());
        Assert.assertEquals(5, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(2, cart.getQuantityByProduct(axeDeo));
        Assert.assertEquals(399.93, priceCalculator.getTotalPrice(), 0.0);
    }

    @Test
    public void testSetTaxToCart() {
        priceCalculator.setTaxRate(12.5);
        Assert.assertEquals(12.5, priceCalculator.getTaxRate(),0.0);
    }

    @Test(expected = AssertionError.class)
    public void testCreateCartWithNegativeTaxRate() {
        //Add tax rate of -1%
        priceCalculator.setTaxRate(-1);
    }

    @Test
    public void testAddTwoProductsToShoppingCartWithTax() {
        //Empty cart and two products. Dove Soap with a unit price of 39.99
        //And another product, Axe Deo with a unit price of 99.99
        Product doveSoap = new Product("Dove Soap", 39.99);
        Product axeDeo = new Product("Axe Deo", 99.99);
        //Add Tax rate of 12.5%
        priceCalculator.setTaxRate(12.5);

        //Adds 2 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap, 2);

        //Checking the shopping cart contain 2 dove soaps and the shopping cart's total price should be equal to 89.98
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(2, cart.getTotalQuantity());
        Assert.assertEquals(2, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(89.98, priceCalculator.getTotalPrice(), 0.0);

        //Adds 2 Axe deo to the shopping cart
        cart.addProduct(axeDeo, 2);

        //Checking the shopping cart contain 2 dove soaps and 2 axe deo
        //The shopping cart's total price should be equal to 314.96
        Assert.assertEquals(2, cart.getProductCount());
        Assert.assertEquals(4, cart.getTotalQuantity());
        Assert.assertEquals(2, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(2, cart.getQuantityByProduct(axeDeo));

        Assert.assertEquals(279.96, priceCalculator.getTotalCartValue(), 0.0);
        Assert.assertEquals(35.00, priceCalculator.getTotalTax(), 0.0);
        Assert.assertEquals(314.96, priceCalculator.getTotalPrice(), 0.0);
    }

    @Test
    public void testCreateCartWithZeroTaxRate() {
        //Empty cart and two products. Dove Soap with a unit price of 39.99 and Tax rate of 0%
        Product doveSoap = new Product("Dove Soap", 39.99);
        priceCalculator.setTaxRate(0);

        //Adds 5 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap, 5);

        //Checking the shopping cart contain 2 dove soaps and the shopping cart's total price should be equal to 89.98
        Assert.assertEquals(1, cart.getProductCount());
        Assert.assertEquals(5, cart.getTotalQuantity());
        Assert.assertEquals(5, cart.getQuantityByProduct(doveSoap));

        Assert.assertEquals(199.95, priceCalculator.getTotalCartValue(), 0.0);
        Assert.assertEquals(0, priceCalculator.getTotalTax(), 0.0);
        Assert.assertEquals(199.95, priceCalculator.getTotalPrice(), 0.0);
    }


    @Test
    public void testOfferBuyXGetY() {
        //Empty cart and two products. Dove Soap with a unit price of 39.99 and Tax rate of 0%
        Product doveSoap = new Product("Dove Soap", 39.99);
        Product axeDeo = new Product("Axe_Deo", 89.99);
        Offer doveOffer = new Offer("Buy2Get1Free", 2, 1, doveSoap);
        doveSoap.setOffer(doveOffer);
        priceCalculator.setTaxRate(12.5);

        //Adds 3 Dove Soaps to the shopping cart
        cart.addProduct(doveSoap, 3);

        Assert.assertEquals(3, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(89.98, priceCalculator.getTotalPrice(), 0.0);
        Assert.assertEquals(39.99, priceCalculator.getDiscount(), 0.0);
        Assert.assertEquals(10.00, priceCalculator.getTotalTax(), 0.0);

        cart.addProduct(doveSoap, 3);

        Assert.assertEquals(6, cart.getQuantityByProduct(doveSoap));
        Assert.assertEquals(179.95, priceCalculator.getTotalPrice(), 0.0);
        Assert.assertEquals(39.99 * 2, priceCalculator.getDiscount(), 0.0);
        Assert.assertEquals(19.99, priceCalculator.getTotalTax(), 0.0);
    }
}