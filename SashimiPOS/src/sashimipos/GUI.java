package sashimipos;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dani_
 */
public class GUI extends javax.swing.JFrame {
    
    private static final String imgFolder = "img" + File.separator;
    private static ArrayList<User> users;
    private User user;
    /**
     * Creates new form GUI
     */
    public GUI() {
//        Item a = new Item(imgFolder + "Apple".toLowerCase() + ".jpg","Apple",80,333);
//        Item o = new Item(imgFolder + "Orange".toLowerCase() + ".jpg","Orange",200,200);
//        Item c = new Item(imgFolder + "Cheese".toLowerCase() + ".jpg","Cheese",310,140);
//        Item c1 = new Item(imgFolder + "Blueberry".toLowerCase() + ".jpg","Muffin",130,140);
//        Item c2= new Item(imgFolder + "Muffin".toLowerCase() + ".jpg","Orangina",81,140);
//        Item c3 = new Item(imgFolder + "Sausage".toLowerCase() + ".jpg","Sausage",130,140);
        
        users = new ArrayList<>();
        LinkedHashMap<Item, Integer> map = new LinkedHashMap<>();
        populateStocksMap(map);
//        map.put(a,3);
//        map.put(o,12);
//        map.put(c,34);
//        map.put(c1,23);
//        map.put(c2,3);
//        map.put(c3,312);
        
        
        Cart stock = new Cart(map); // load items and their remaining quantity from database here
        cartTableModel = new CartTableModel(new Cart(), new String[]{"Image", "Name", "Price", "Amount"});
        stockTableModel = new CartTableModel(stock, new String[]{"Image", "Name", "Price", "Quantity available"});
        //load usernames here
        
        userDropdownModel = populateUsersDropdown();
        
        initComponents();
        populateItemsTable(stockTableModel);
        cartTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stockTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//        stockTableModel.addRow(a);
//        stockTableModel.addRow(o);
//        stockTableModel.addRow(c);
        
    }
    


    private void populateStocksMap(LinkedHashMap<Item, Integer> map){
        DBOperations dbops = new DBOperations();
        Connection c = dbops.getConnection();
        ResultSet items = dbops.getItems(c);
        try {
            while(items.next()){
                int id = items.getInt("id");
                String image = items.getString("icon");
                String name = items.getString("name");
                int price = items.getInt("price");
                float weight = items.getFloat("weight");
                int stock = items.getInt("quantity");
                Item a = new Item(id,image,name,price,weight);
                System.out.println(a.getWeight());
                map.put(a, stock);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateItemsTable(CartTableModel stockTableModel){
        DBOperations dbops = new DBOperations();
        Connection c = dbops.getConnection();
        ResultSet items = dbops.getItems(c);
        try {
            while(items.next()){
                int id = items.getInt("id");
                String image = items.getString("icon");
                System.out.println("image path");
                System.out.println(image);
                String name = items.getString("name");
                int price = items.getInt("price");
                float weight = items.getFloat("weight");
                int stock = items.getInt("quantity");
                Item a = new Item(id,image,name,price,weight);
                System.out.println(a.getWeight());
                stockTableModel.addRow(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private DefaultComboBoxModel populateUsersDropdown(){
        DBOperations dbops = new DBOperations();
        Connection c = dbops.getConnection();
        ResultSet items = dbops.getUsers(c);
        try {
            while(items.next()){
                String username = items.getString("username");
                System.out.println("image path");
                int id = items.getInt("id");
                int balance = items.getInt("balance");
                String password = items.getString("password");
                String facepixels = items.getString("facepixels");
                User u = new User(id,username,balance,password,facepixels);
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] userNames = new String[users.size()];
        for(int i = 0; i < userNames.length; i++) userNames[i] = users.get(i).getUsername();
        return new DefaultComboBoxModel(userNames);
    }      
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablePanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        totalPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        weightLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        cartPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        userDropdown = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // this table will hold Stock, which the StockManager will manage
        // at this stage, load all items from database into StockManager
        stockTable.setModel(stockTableModel);
        stockTable.setRowHeight(60);
        jScrollPane3.setViewportView(stockTable);

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );

        jLabel1.setText("Total price:");

        totalLabel.setText("0");

        jLabel2.setText("¥");

        weightLabel.setText("0");

        jLabel3.setText("Total weight:");

        jLabel4.setText("gm");

        javax.swing.GroupLayout totalPanelLayout = new javax.swing.GroupLayout(totalPanel);
        totalPanel.setLayout(totalPanelLayout);
        totalPanelLayout.setHorizontalGroup(
            totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(totalPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(totalPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(weightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(493, Short.MAX_VALUE))
        );
        totalPanelLayout.setVerticalGroup(
            totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalLabel)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(totalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weightLabel)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)))
        );

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cartTable.setModel(cartTableModel);
        cartTable.setRowHeight(60);
        jScrollPane2.setViewportView(cartTable);

        javax.swing.GroupLayout cartPanelLayout = new javax.swing.GroupLayout(cartPanel);
        cartPanel.setLayout(cartPanelLayout);
        cartPanelLayout.setHorizontalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
        );
        cartPanelLayout.setVerticalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Take items");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        userDropdown.setModel(userDropdownModel);
        userDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userDropdownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(removeBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(totalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeBtn)))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        Item selection = cartTableModel.getItemAt(cartTable.getSelectedRow());
        if(selection == null) return;
        cartTableModel.removeRow(selection);
        stockTableModel.addRow(selection);
        cartTable.clearSelection();
        
        Cart cart = cartTableModel.getCart();
        int selectedIndex = stockTable.getSelectedRow() - cart.size();
        cartTable.clearSelection();
        totalLabel.setText(String.valueOf(cart.getTotal()));
        weightLabel.setText(String.valueOf(cart.getWeight()));
        cartTable.setRowSelectionInterval(selectedIndex-1,selectedIndex-1);
    }//GEN-LAST:event_removeBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        if(user == null){
            JOptionPane.showMessageDialog(this, "Must select a user first.");
            return;
        }
        Item selection = stockTableModel.getItemAt(stockTable.getSelectedRow());
        if(selection == null) return;
        cartTableModel.addRow(selection);
        
        Cart stock = stockTableModel.getCart();
        stockTableModel.removeRow(selection);
        
        int selectedIndex = stockTable.getSelectedRow() - stock.size();
        stockTable.clearSelection();
        totalLabel.setText(String.valueOf(cartTableModel.getCart().getTotal()));
        stockTable.setRowSelectionInterval(selectedIndex-1,selectedIndex-1);
        weightLabel.setText(String.valueOf(cartTableModel.getCart().getWeight()));
    }//GEN-LAST:event_addBtnActionPerformed

    private void userDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userDropdownActionPerformed
        String username = (String) userDropdown.getSelectedItem();
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equalsIgnoreCase(username)){
                this.user = users.get(i);
                return;
            }
        }
    }//GEN-LAST:event_userDropdownActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int purchaseTotal = cartTableModel.getCart().getTotal(); // include cancel transaction
        if(user.getBalance() >= purchaseTotal){
            // subtract item quantity from database
            // subtract user balance
            this.user = null;
            updateTableonSuccess(stockTableModel.getCart());
            cartTableModel.emptyCart();
            totalLabel.setText("0");
            weightLabel.setText("0");
            
            JOptionPane.showMessageDialog(this, "Thank you for your purchase. To cancel your purchase, plase press");
        }else{
            JOptionPane.showMessageDialog(this, "Your account balance is too low to cover this transaction!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void updateTableonSuccess(Cart cart){
        DBOperations dbops = new DBOperations();
        Connection c = dbops.getConnection();
        dbops.updateItemsAfterTransactionComplete(c, cart);
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    
    private DefaultTableModel itemTableModel;
    private CartTableModel cartTableModel;
    private CartTableModel stockTableModel;
    private DefaultComboBoxModel userDropdownModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel cartPanel;
    private javax.swing.JTable cartTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton removeBtn;
    private javax.swing.JTable stockTable;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JPanel totalPanel;
    private javax.swing.JComboBox<String> userDropdown;
    private javax.swing.JLabel weightLabel;
    // End of variables declaration//GEN-END:variables
}
