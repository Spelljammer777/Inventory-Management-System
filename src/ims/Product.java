/*
 * This program has been written by Richard Lewis
 * Title = Inventory Management System
 * 
 */
package ims;

//import static ims.IMSmain.dataPart;
//import static ims.IMSmain.selectedRecord;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

/**
 *
 * @author Richard Lewis
 */
public class Product {
    // associatedParts is set as an ObservableList and seperate from the Product 
    // object. This is because a Product can contain more than one Part. The associatedParts
    // list acts as a linking table between Parts and Products. For instance, Product #1 has 
    // Parts # 2 and 3 associated with it. This can be seen here:
    //   new partprod(1, 2),    
    //   new partprod(1, 3),
    // The next line is "new partprod(2, 6),". That means Product 2 has Part 6 associated with it.
    
    public static ObservableList<partprod> associatedParts =
        FXCollections.<partprod>observableArrayList(
            new partprod(1, 2),    
            new partprod(1, 3),
            new partprod(2, 6),    
            new partprod(2, 9),
            new partprod(3, 5),    
            new partprod(4, 6),
            new partprod(4, 7),    
            new partprod(5, 12),
            new partprod(6, 12),    
            new partprod(6, 13),
            new partprod(7, 14),    
            new partprod(8, 17),
            new partprod(9, 8),    
            new partprod(9, 18),
            new partprod(10, 1),    
            new partprod(10, 4),
            new partprod(11, 9),
            new partprod(11, 15),
            new partprod(11, 16)
        );
        private final SimpleIntegerProperty productID;
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty instock;
        private final SimpleDoubleProperty price;
        private final SimpleIntegerProperty min;
        private final SimpleIntegerProperty max;
         
        public Product(int productID, String name, int instock, int min, int max, double price ) {
            this.productID = new SimpleIntegerProperty(productID);
            this.name = new SimpleStringProperty(name);
            this.instock = new SimpleIntegerProperty(instock);
            this.min = new SimpleIntegerProperty(min);
            this.max = new SimpleIntegerProperty(max);
            this.price = new SimpleDoubleProperty(price);
            
        }
        
        public int getProductID() {
            return productID.get();
        }
 
        public void setProductID(int ID) {
            productID.set(ID);
        }
        
        public String getName() {
            return name.get();
        }
 
        public void setName(String name) {
            this.name.set(name);
        }
 
        public int getInstock() {
            return instock.get();
        }
 
        public void setInstock(int instock) {
            this.instock.set(instock);
        }

        public int getMin() {
            return min.get();
        }
 
        public void setMin(int min) {
            this.min.set(min);
        }
        
        public int getMax() {
            return max.get();
        }
 
        public void setMax(int max) {
            this.max.set(max);
        }
 
        public double getPrice() {
            return price.get();
        }
 
        public void setPrice(double price) {
            this.price.set(price);
        }
        
         public static int updatePart() {
            // functionality handled elsewhere, in IMSmain.
            return(0);
        }

         
         public static void addAssociatedPart(String selectedProdID, String partID) {
              associatedParts.add(new partprod(
              Integer.parseInt(selectedProdID),        
              Integer.parseInt(partID)
              ));
        }
         
         public static boolean removeAssociatedPart(partprod selectedRecord4) {
            associatedParts.remove(selectedRecord4);
            
            return(true);
        }
         
         public static int lookupAssociatedPart(TableView<Part> tablePartc, int row) {

            Part selectedRecord = (Part)tablePartc.getItems().get(row); 
            return(1);
        }

        
        
 
        
        
        
    }
        
