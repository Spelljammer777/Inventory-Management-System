/*
 * This program has been written by Richard Lewis
 * Title = Inventory Management System
 * 
 */
package ims;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Richard Lewis
 */
public class inHouse extends Part {
    
     //private final SimpleStringProperty companyName;    
        private final SimpleStringProperty machineID;
  
        public inHouse (int partID, String name, int instock, int partMin, int partMax,
                double price, String machineID) {
            super(partID, name, instock, partMin, partMax, price );
            ////super(partID, name, instock, partMin, partMax, price, idName);
            this.machineID = new SimpleStringProperty(machineID);
        }
        //--- finish these 2 below methods
                
        public static int setMachineID(Label comPartsLabel, TextField comParts) {
                    //System.out.println(System.getenv("COMPUTERNAME"));
                    System.out.println("In-House Selected");
                    comPartsLabel.setText("Machine ID");
                    comParts.setText("504738");
                    comParts.setEditable(false);
                    return(1);
            
        }
          public String getMachineID() {
            return machineID.get();
        }
//         public static int getMachineID() {
//            int machineID = 504738;
//            return(machineID);
//        }
        

        
}
    

