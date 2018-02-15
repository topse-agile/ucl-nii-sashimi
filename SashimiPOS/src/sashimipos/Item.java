/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sashimipos;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author dani_
 */
public class Item {
    private Icon icon;
    private String name;
    private int price;
    private int weight;

    public Item(String picPath, String name, int price, int weight){
        icon = new ImageIcon(picPath);
        this.name = name;
        this.price = price;
        this.weight = weight;
    }
    
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon picture) {
        this.icon = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    @Override
    public boolean equals(Object obj){
       if(this == obj) return true;
       if(obj instanceof Item){
           Item that = (Item) obj;
           if(this.name.equals(that.name)) return true;
       }
       return false;
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        return result;
    }
    
}
