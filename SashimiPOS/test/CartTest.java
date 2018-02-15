/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sashimipos.Cart;
import sashimipos.Item;

/**
 *
 * @author dani_
 */
public class CartTest {
    
    
    public CartTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testCartTotalAfterAddingItem() {
        Cart cart = new Cart();
        int applePrice = 80;
        int orangePrice = 100;
        Item apple = new Item("apple.jpg","Apple",applePrice,250);
        Item apple2 = new Item("apple.jpg","Apple",applePrice,250);
        Item orange = new Item("orange.jpg", "Orange", orangePrice, 200);
        
        cart.addItem(apple);
        assertThat(cart.getTotal(), is(applePrice));
        
        cart.addItem(orange);
        assertThat(cart.getTotal(), is(applePrice + orangePrice)); 
        
        cart.addItem(apple2);
        assertThat(cart.getTotal(), is(applePrice*2 + orangePrice));
    }

    @Test
    public void testCartTotalAfterRemovingItem() {
        Cart cart = new Cart();
        int applePrice = 80;
        int orangePrice = 100;
        Item apple = new Item("apple.jpg","Apple",applePrice,250);
        Item apple2 = new Item("apple.jpg","Apple",applePrice,250);
        Item orange = new Item("orange.jpg", "Orange", orangePrice, 200);
        
        cart.addItem(apple);
        cart.addItem(orange);
        cart.addItem(apple2);
        
        cart.removeItem(apple);
        assertThat(cart.getTotal(), is(applePrice + orangePrice));
        
        cart.removeItem(apple2);
        assertThat(cart.getTotal(), is(orangePrice));
        
        cart.removeItem(orange);
        assertThat(cart.getTotal(), is(0));
    }    
    
    @Test
    public void testCartSize() {
        Cart cart = new Cart();
        int applePrice = 80;
        int orangePrice = 100;
        Item apple = new Item("apple.jpg","Apple",applePrice,250);
        Item apple2 = new Item("apple.jpg","Apple",applePrice,250);
        Item orange = new Item("orange.jpg", "Orange", orangePrice, 200);
        
        cart.addItem(apple);
        cart.addItem(orange);
        cart.addItem(apple2);
        
        assertThat(cart.size(), is(2));
        
        cart.removeItem(apple2);
        assertThat(cart.size(), is(2));
        
        cart.removeItem(apple);
        assertThat(cart.size(), is(1));
        
        
    }      
    
    
}
