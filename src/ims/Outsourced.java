/*
 * This program has been written by Richard Lewis
 * Title = Inventory Management System
 * 
 */
package ims;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Richard Lewis
 */
public class Outsourced extends Part{
     private final SimpleStringProperty Outsourced;
  
 
        public Outsourced (int partID, String name, int instock, int partMin, 
                int partMax, double price, String Outsourced) {
            super(partID, name, instock, partMin, partMax, price);
            this.Outsourced = new SimpleStringProperty(Outsourced);
        }
        //--- finish these 2 below methods
        public static int setCompanyName(Label comPartsLabel, TextField comParts) {
                    System.out.println("Outsourced Selected");
                    comPartsLabel.setText("Company Name");
                    if (comParts.getText().isEmpty()){
                        comParts.setText("CompanyName??");
                    }
                    if (comParts.getText().matches("504738")){
                        comParts.setText("CompanyName??");
                    }
                    
                    comParts.setEditable(true);
            return(1);
        }
         
         public String getOutsourced() {
            // functionality handled elsewhere, in IMSmain.
             return Outsourced.get();
        }
}
