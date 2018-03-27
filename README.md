Shopping Cart
=============
Version number: 324d4ec0c12127922c260408e8d8220d0f631bea

The Shopping cart application allow a user to add products to a shopping cart, and then calculate the total price and tax amounts for the items contained in the cart. 
 
This application is developed using [Test-Driven Development](http://en.wikipedia.org/wiki/Test-driven_development) (TDD).

Tools and Technologies used
---------------------------
 * Java
 * Maven 
 * JUnit
 
Prerequisite
-------------
1. Java (1.7 or above)
2. Maven (3.3.9)

Getting started
---------------
1. Goto Project folder
2. Install maven dependencies
   ```sh
   mvn install
   ```
2. To run test cases 
   ```sh
   mvn test
   ```
3. To run code coverage
    ```sh
   mvn jacoco:report
   ```
4. Goto target>site folder and open index.html to see code coverage report.