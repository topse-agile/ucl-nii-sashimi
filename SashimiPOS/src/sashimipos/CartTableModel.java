package sashimipos;


import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class CartTableModel extends AbstractTableModel {

    private Cart cart;
    private String[] header;

    public CartTableModel(Cart cart, String[] header) { // linkedhashmap to retain order
        this.cart = cart;
        this.header = header;
    }

    @Override
    public int getRowCount(){
        return cart.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    public void addRow(Item item){
        cart.addItem(item);
        fireTableRowsInserted(0,cart.size());
    }

    public void removeRow(Item item){
        cart.removeItem(item);
        fireTableRowsInserted(0,cart.size());
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "n/a";
        Item itemAt = getItemAt(rowIndex);
        
        switch (columnIndex) {
            case 0:
                value = itemAt.getIcon();
                break;
            case 1:
                value = itemAt.getName();
                break;
            case 2:
                value = itemAt.getPrice();
                break;
            case 3:
                value = cart.count(itemAt);
                break;
        }

        return value;

    }

    @Override
    public Class<?> getColumnClass(int column) {
        return getValueAt(0, column).getClass();// Return the class that best represents the column...
    }

    /* Override this if you want the values to be editable...
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //....
    }
    */

    @Override
    public String getColumnName(int col){
        return header[col];
    }
    
    /**
     * This will return the user at the specified row...
     * @param row
     * @return 
     */
    public Item getItemAt(int row) {
        return cart.getItem(row);
    }

    public Cart getCart(){
        return cart;
    }
}