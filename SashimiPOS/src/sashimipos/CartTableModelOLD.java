package sashimipos;


import java.util.LinkedHashMap;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class CartTableModelOLD extends AbstractTableModel {

    private LinkedHashMap<Item, Integer> items;
    private String[] header;

    public CartTableModelOLD(LinkedHashMap<Item, Integer> items) { // linkedhashmap to retain order
        if(items == null) this.items = new LinkedHashMap<>();
        else this.items = new LinkedHashMap<>(items);
        header = new String [] {"Picture", "Item", "Price", "Amount"};
        
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    public void addRow(Item item){
        if(items.containsKey(item)){
            int count = items.get(item);
            count += 1;
            items.put(item, count);
        }else{
            items.putIfAbsent(item, 1);
        }
        fireTableRowsInserted(0,items.size());

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
                value = items.get(itemAt);
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
        int i = 0;
        for(Item item : items.keySet()){
           if(i == row){
               return item;
           }
           i++;
        }
        return null;
    }

}