package sashimipos;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author zawad
 */
public class DBOperations {
    private String tableName = "gene_two_column";
    private Connection connection;
    private Statement stmt;
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MySQL?zeroDateTimeBehavior=convertToNull","root", "admin"
                );

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your SQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (connection != null) {
            // System.out.println("You made it, take control your database now!");
            return connection;
        } else {
            // System.out.println("Failed to make connection!");
            return null;
        }
    }
    
    public ResultSet getItems(Connection c){
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM items;");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet getWeight(Connection c, int itemId){
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM items WHERE id = "+itemId+";");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateBalance(User u){
        
    }

    public ResultSet getUsers(Connection c){
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM balances;");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
    
    public ResultSet getUserInformation(Connection c, int userId){
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM balances WHERE id = "+userId+";");
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
    
    public void updateItemsAfterTransactionComplete(Connection c, Cart cart){
        try {
            stmt = c.createStatement();
            String query = "UPDATE items SET quantity = ? WHERE id = ?";
            LinkedHashMap<Item, Integer> items = cart.getItems();
            for(Item item : items.keySet()){
                int id = item.getId();
                ResultSet r = stmt.executeQuery("SELECT * FROM ITEMS WHERE ID = "+id+";");
                int quantity = r.getInt("quantity");
                int updatedQuantity = quantity - items.get(item);
                stmt.executeQuery("UPDATE items SET quantity = "+updatedQuantity+" WHERE id = "+id+";");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateItemsAfterTransactionComplete2(Connection c, Cart cart){
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM balances;");
            LinkedHashMap<Item, Integer> items = cart.getItems();
            for(Item item : items.keySet()){
                int id = item.getId();
                ResultSet r = stmt.executeQuery("SELECT * FROM ITEMS WHERE ID = "+id+";");
                int quantity = r.getInt("quantity");
                int updatedQuantity = quantity - items.get(item);
                stmt.executeQuery("UPDATE items SET quantity = "+updatedQuantity+" WHERE id = "+id+";");
            }
        }catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    
}