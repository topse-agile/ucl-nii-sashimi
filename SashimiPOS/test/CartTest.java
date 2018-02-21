/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.hamcrest.CoreMatchers.is;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testCartTotalAfterAddingItem() {
        Cart cart = new Cart();
        int applePrice = 80;
        int orangePrice = 100;
        Item apple = new Item(1,"apple.jpg","Apple",applePrice,250);
        Item apple2 = new Item(2,"apple.jpg","Apple",applePrice,250);
        Item orange = new Item(3,"orange.jpg", "Orange", orangePrice, 200);
        
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
        Item apple = new Item(1,"apple.jpg","Apple",applePrice,250);
        Item apple2 = new Item(2,"apple.jpg","Apple",applePrice,250);
        Item orange = new Item(3,"orange.jpg", "Orange", orangePrice, 200);
        
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
        Item apple = new Item(1,"apple.jpg","Apple",applePrice,250);
        Item apple2 = new Item(2,"apple.jpg","Apple",applePrice,250);
        Item orange = new Item(3,"orange.jpg", "Orange", orangePrice, 200);
        
        cart.addItem(apple);
        cart.addItem(orange);
        cart.addItem(apple2);
        
        assertThat(cart.size(), is(2));
        
        cart.removeItem(apple2);
        assertThat(cart.size(), is(2));
        
        cart.removeItem(apple);
        assertThat(cart.size(), is(1));
        
        cart.removeItem(orange);
        assertThat(cart.size(), is(0));
    }      
    
    
}
