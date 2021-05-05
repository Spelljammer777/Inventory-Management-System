/*
 * This program has been written by Richard Lewis
 * Title = Inventory Management System
 * 
 */
package ims;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;





/**
 *
 * @author Richard Lewis
 */
public class Inventory  {
    
    public static ObservableList<Product> dataProd =
        FXCollections.observableArrayList(
            new Product(1, "Product1", 15, 3, 100, 22.95),
            new Product(2, "Product2", 25, 3, 100, 32.00),
            new Product(3, "Product3", 57, 3, 100, 12.99),
            new Product(4, "Product4", 7, 3, 100, 14.95),
            new Product(5, "Product5", 9, 3, 100, 27.99),
            new Product(6, "Product6", 2, 3, 100, 21.00),
            new Product(7, "Product7", 15, 3, 100, 12.00),
            new Product(8, "Product8", 120, 3, 100, 19.15),
            new Product(9, "Product9", 1, 3, 100, 49.95),
            new Product(10, "Product10", 15, 3, 100, 11.95),
            new Product(11, "Product11", 1, 3, 100, 39.95)
        );
    
    public static ObservableList<Part> dataPart =
        FXCollections.<Part>observableArrayList(
            new inHouse(1, "Part1", 15, 3, 100, 1.95, "504738"),
            new Outsourced(2, "Widget2", 25, 3, 100, 12.00, "VanIsleCorp"),
            new inHouse(3, "Part3", 57, 3, 100, 22.99, "504738"),
            new Outsourced(4, "Quadifier", 3, 100, 7, 4.95, "VanIsleCorp"),
            new Outsourced(5, "Fiverr", 9, 3, 100, 17.99, "VanIsleCorp"),
            new inHouse(6, "Sixey", 2, 3, 100, 11.00, "504738"),
            new Outsourced(7, "Seven", 15, 3, 100, 12.00, "West Coast Inc"),
            new Outsourced(8, "Ate2Much", 120, 3, 100, 19.15, "West Coast Inc"),
            new Outsourced(9, "Niner", 1, 3, 100, 49.95, "West Coast Inc"),
            new inHouse(10, "Tenly-xJ4", 15, 3, 100, 1.95, "504738"),
            new inHouse(11, "Widget-WWY", 25, 3, 100, 12.00, "504738"),
            new Outsourced(12, "Widget-345", 5, 3, 100, 22.99, "West Coast Inc"),
            new Outsourced(13, "Widget-xc4", 71, 3, 100, 14.95, "West Coast Inc"),
            new Outsourced(14, "Widget-awe", 943, 3, 100, 17.99, "VanIsleCorp"),
            new Outsourced(15, "Widget-123", 20, 3, 100, 11.00, "VanIsleCorp"),
            new inHouse(16, "Widget-789", 15, 3, 100, 12.00, "504738"),
            new Outsourced(17, "Widget-121", 12, 3, 100, 9.15, "West Coast Inc"),
            new Outsourced(18, "Widget-007", 1, 3, 100, 19.95, "West Coast Inc")
        );


    
        
        //+++++++++++++++++++++++++ add Product ++++++++++++++++++++++++++++++++++++++++++++++
        public static int addProduct(Float pricetotal, TableView<Part> tablePartc,
                TextField priceProduct, TextField nameProduct,TextField instockProduct,
                TextField maxProduct,TextField minProduct, Label modifyProductLabel,
                ObservableList dataProd, TextField idProduct, TableView<partprod>
                tablepartprod, Product selectedRecordProd, ObservableList associatedParts 
                ) {
            
            // add product code starts here....
        //
            String selectedProdID = idProduct.getText();     
            Boolean cleartext = true;
              
            // Error when a field is left blank
                if (nameProduct.getText().equals("") || instockProduct.getText().equals("") ||
                    priceProduct.getText().equals("") || maxProduct.getText().equals("") 
                    || minProduct.getText().equals("")) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("All fields must be filled in!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
            
             // cost of product should be greater than price of all parts
             pricetotal = 0f;
             for (int row = 0; row < tablePartc.getItems().size(); row++) {
                
                String price = (String.valueOf(tablePartc.getItems().get(row).getPrice()));
                Float pricenum = Float.parseFloat(price);
                pricetotal += pricenum;
             }   
             System.out.println("TOTAL of all parts in prod = " + pricetotal);
             Float priceprodfloat = Float.parseFloat(priceProduct.getText());
             // Error when a prod price is less than part price totals
                if (priceprodfloat < pricetotal) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("Product price must be greater than or equal to " + pricetotal );
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
             
                                               
                // Error when min part is larger than max part OR max part is smaller than min part
                if (Integer.parseInt(maxProduct.getText()) < Integer.parseInt(minProduct.getText())){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("The min product must be smaller or equal to the max product!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
                
                // Error when min part is less than 1
                if (Integer.parseInt(minProduct.getText()) < 1){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("The min product must be 1 or more!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
               
                
                // Error when inventory is less than min part
                if (Integer.parseInt(instockProduct.getText()) < Integer.parseInt(minProduct.getText())){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("The inventory amount must be greater than or equal to the min product!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
                
                // Error when inventory is greater than max part
                if (Integer.parseInt(instockProduct.getText()) > Integer.parseInt(maxProduct.getText())){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("The inventory amount must be smaller or equal to the max prodctuct!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
                
                // Error when no part has been added to a product
                if (tablePartc.getItems().size() < 1){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("The product must contain at least one part!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }            
            
if (cleartext == true) { // only runs if no errors occur
            System.out.println("Saving Product!");
            String tLabel = modifyProductLabel.getText();
            //save product code

            // saves data to dataProd observable list
            dataProd.add(new Product(
            Integer.parseInt(idProduct.getText()),
            nameProduct.getText(),
            Integer.parseInt(instockProduct.getText()),        
            Integer.parseInt(minProduct.getText()),
            Integer.parseInt(maxProduct.getText()),
            Double.parseDouble(priceProduct.getText())
            ));
            
//            if this is a modify product action, remove the original record and add
//                    a duplicate, but with changes.
            if (tLabel == "Modify Product"){
                System.out.println("modify product---------");
                dataProd.remove(selectedRecordProd);
            } 
            
            
            // removes prodID and partID from datapartprod
            //int max = tablepartprod.getItems().size();
            for (int count = 0; count < 10; count++) {
                
                for (int row = 0; row < tablepartprod.getItems().size(); row++) {

                   String prodID = (String.valueOf(tablepartprod.getItems().get(row).getIDofprod()));
                   String partID = (String.valueOf(tablepartprod.getItems().get(row).getIDofpart()));
                   partprod selectedRecord4 = (partprod)tablepartprod.getItems().get(row); 

                   if (prodID.equals(selectedProdID)) {
                       Product.removeAssociatedPart(selectedRecord4);

                       break;
                   }
                }
            }
            
            
            
            // add prodID and partID to datapartprod
            // from the tablepartc table
            for (int row = 0; row < tablePartc.getItems().size(); row++) {
            
                String partID = (String.valueOf(tablePartc.getItems().get(row).getPartID()));
                    
                Product.addAssociatedPart(selectedProdID, partID);

                
            }

            
            // clears the form so it is ready for next time.
            idProduct.clear();
            nameProduct.clear();
            instockProduct.clear();
            minProduct.clear();
            maxProduct.clear();
            priceProduct.clear();  
            return (1);
            
            }
return (0); // if error
            
        }
        //+++++++++++++++++++++++++ remove Product ++++++++++++++++++++++++++++++++++++++++++++++
         public static int removeProduct(TableView<Part> tablePartc, Product selectedRecordProd) {
             
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete this product? ", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                if (tablePartc.getItems().size() >=0){
                   Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error Message");
                    alert1.setHeaderText("Error message!");
                    alert1.setContentText("This product contains at least one part and cannot be deleted!");
                    alert1.showAndWait(); 
                }
                else { 
                    
                    Product.associatedParts.remove(selectedRecordProd);
                    System.out.println("Delete Product!");
                }
            } 
            
            return (0);
        }
         
//+++++++++++++++++++++++++ lookup Product ++++++++++++++++++++++++++++++++++++++++++++++
        public static int lookupProduct(String lowerCaseFilter, TableView<Product> 
                tableProduct) {
            // Search functionality
            ObservableList<Product> subentries = FXCollections.observableArrayList();
            
            tableProduct.setItems(dataProd);
            long count = tableProduct.getColumns().stream().count();
            for (int row = 0; row < tableProduct.getItems().size(); row++) {
                for (int col = 0; col < count; col++) {
                    String entry = "" + tableProduct.getColumns().get(col).getCellData(row);
                    if (entry.toLowerCase().contains(lowerCaseFilter)) {
                        subentries.add(tableProduct.getItems().get(row));
                        break;
                    }
                }
            }            
            if (lowerCaseFilter != "") {
              tableProduct.setItems(subentries);
            }            
            System.out.println("Searching for Product named " + lowerCaseFilter + ".");
            
            return (0);
        }
//+++++++++++++++++++++++++ update Product ++++++++++++++++++++++++++++++++++++++++++++++
        public static int updateProduct(TableView<Part> tablePartc, ObservableList dataPartc,
        TableView<partprod> tablepartprod, TextField idProduct, TableView<Part> tablePartb,
         TableView<Product> tableProduct) {
            for (int row = 0; row < tablePartc.getItems().size(); row++) {
                int getResult = Product.lookupAssociatedPart(tablePartc, row);
//              Part selectedRecord = (Part)tablePartc.getItems().get(row); 
              dataPartc.removeAll(dataPartc);
            }
            for (int row = 0; row < tablepartprod.getItems().size(); row++) {
                
                    
                    String entry = (String.valueOf(tablepartprod.getItems().get(row).getIDofprod()));
                    String PartIDinprod = (String.valueOf(tablepartprod.getItems().get(row).getIDofpart()));
                    String selectedProdID = idProduct.getText();
                    
                   
                    if (entry.equals(selectedProdID)) {
                        for (int row1 = 0; row1 < tablePartb.getItems().size(); row1++) {
                            String parttemp = (String.valueOf(tablePartb.getItems().get(row1).getPartID()));
                            if (parttemp.equals(PartIDinprod)) {
                                Part selectedRecord3 = (Part)tablePartb.getItems().get(row1);
                                dataPartc.add(selectedRecord3);

                            }
                        }
                    }
            }
            
            // ------
            tableProduct.setItems(dataProd);// returns prod table to normal, removes sorting
            return(0);
        }
        
        
        
//+++++++++++++++++++++++++ Add Part ++++++++++++++++++++++++++++++++++++++++++++++
 public static int addPart(Label addPartLabel, TextField instockParts, TextField minParts,
            TextField maxParts, TextField nameParts, TextField comParts,  
            TextField idParts, TextField priceParts, Part selectedRecord) {
            
             Boolean cleartext = true;
             String tLabel = addPartLabel.getText();
            
                System.out.println(tLabel + "Modify Part");
                // Error when a field is left blank
                if (instockParts.getText().equals("") || maxParts.getText().equals("") ||
                    minParts.getText().equals("") || nameParts.getText().equals("") 
                    || comParts.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Error Message");
                     alert.setHeaderText("Error message!");
                     alert.setContentText("All fields must be filled in!");
                     alert.showAndWait(); 
                     cleartext = false;
                     return(0);
                     }
                
                
                                               
                // Error when min part is larger than max part OR max part is smaller than min part
                if (Integer.parseInt(maxParts.getText()) < Integer.parseInt(minParts.getText())){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("The min part must be smaller or equal to the max part!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
                
                // Error when min part is less than 1
                if (Integer.parseInt(minParts.getText()) < 1){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("The min part must be 1 or more!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
                // Error when inventory is less than min part
                if (Integer.parseInt(instockParts.getText()) < Integer.parseInt(minParts.getText())){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("The inventory amount must be greater than or equal to the min part!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
                // Error when inventory is greater than max part
                if (Integer.parseInt(instockParts.getText()) > Integer.parseInt(maxParts.getText())){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Error message!");
                    alert.setContentText("The inventory amount must be smaller or equal to the max part!");
                    alert.showAndWait(); 
                    cleartext = false;
                    return(0);
                }
                
                
                
                
                if (cleartext == true) { // only runs if no errors occur
                dataPart.add(new inHouse(
                Integer.parseInt(idParts.getText()),
                nameParts.getText(),
                Integer.parseInt(instockParts.getText()),        
                Integer.parseInt(minParts.getText()),
                Integer.parseInt(maxParts.getText()),
                Double.parseDouble(priceParts.getText()),
                comParts.getText()
                ));
                
                
                if (tLabel == "Modify Part"){
                System.out.println("modify part---------");

                dataPart.remove(selectedRecord);
                }
               
                System.out.println("Saving Part! " + comParts.getText());
                idParts.clear();
                nameParts.clear();
                instockParts.clear();
                priceParts.clear();
                maxParts.clear();
                minParts.clear();
                comParts.clear();
                
                
            } //ends cleartext section if no errors occurred
            
            //save part code ends here

            return (1);
        }
//+++++++++++++++++++++++++ Delete Part ++++++++++++++++++++++++++++++++++++++++++++++
         public static int deletePart(Part selectedRecord) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete this part? ", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.YES) {
                   dataPart.remove(selectedRecord);                   
                   System.out.println("Delete Part!"); 
                   return(1);
            }
            
            return(0); 
        }
         //++++++++++++++ Lookup Part ++++++++++++++++++++++++++++++++++++++++++++++
        public static int lookupPart(TextField searchParts, TableView<Part> tablePart) {
            
            
            
            String lowerCaseFilter = searchParts.getText().toLowerCase();
            ObservableList<Part> subentriesPart = FXCollections.observableArrayList();
            
            tablePart.setItems(dataPart);
            long count = tablePart.getColumns().stream().count();
            for (int row = 0; row < tablePart.getItems().size(); row++) {
                for (int col = 0; col < count; col++) {
                    String entry = "" + tablePart.getColumns().get(col).getCellData(row);
                    if (entry.toLowerCase().contains(lowerCaseFilter)) {
                        subentriesPart.add(tablePart.getItems().get(row));
                        break;
                    }
                }
            }            
            if (lowerCaseFilter != "") {
              tablePart.setItems(subentriesPart);
            }            
            System.out.println("Searching for Part named " + lowerCaseFilter + ".");


return (0);

        }
        
//++++++++++++++++++++++++++ Update Part ++++++++++++++++++++++++++++++++++++++++++++++
        public static int updatePart(TableView<Part> tablePart, TextField idParts,
            int rowIndex) {
            Part selectedRecord = (Part)tablePart.getItems().get(rowIndex);
            idParts.setText(String.valueOf(selectedRecord.getPartID()));
            idParts.setEditable(false); 
            tablePart.setItems(dataPart);
                        
            return(0);
        }
        
        
}


