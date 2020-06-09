package com.example.myshoppingreminder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    RegistrationActivity register = new RegistrationActivity();

    Product product = new Product("Hat", "small black", 4, "teako@gmail.com");

    @Test
    public void validateProduct(){
        assertEquals("Hat", product.getName());
        assertEquals("small black", product.getDetail());
        assertEquals(4, product.getPriority());
        assertEquals("teako@gmail.com", product.getEmail());
    }

}
