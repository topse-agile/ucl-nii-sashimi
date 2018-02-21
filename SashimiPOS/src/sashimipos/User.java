/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sashimipos;

/**
 *
 * @author dani_
 */
public class User {
    private int id;
    private String username;
    private int balance;
    private String password;
    private String facepixels;

    public User(int id, String username, int balance, String password, String facepixels){
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.password = password;
        this.facepixels = facepixels;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacepixels() {
        return facepixels;
    }

    public void setFacepixels(String facepixels) {
        this.facepixels = facepixels;
    } 
}
