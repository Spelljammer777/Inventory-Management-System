/*
 * This program has been written by Richard Lewis
 * Title = Inventory Management System
 * 
 */
package ims;


import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Richard Lewis
 */
public class partprod {
      private final SimpleIntegerProperty idofprod;
      private final SimpleIntegerProperty idofpart;

    public partprod(int prod, int part) {
            this.idofprod = new SimpleIntegerProperty(prod);
            this.idofpart = new SimpleIntegerProperty(part);
          }
      
        public int getIDofprod() {
            return idofprod.get();
        }
 
        public void setIDofprod(int ID) {
            idofprod.set(ID);
        }
        public int getIDofpart() {
            return idofpart.get();
        }
 
        public void setIDofpart(int ID) {
            idofpart.set(ID);
        }
        

        
                
        
        
}


