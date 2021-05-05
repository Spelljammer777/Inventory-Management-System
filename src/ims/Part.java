/*
 * This program has been written by Richard Lewis
 * Title = Inventory Management System
 * 
 */
package ims;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Richard Lewis
 */
public abstract class Part {
//public class Part {
 
        private final SimpleIntegerProperty partID;
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty instock;
        private final SimpleDoubleProperty price;
        private final SimpleIntegerProperty partMin;
        private final SimpleIntegerProperty partMax;
       //// private final SimpleStringProperty idName;


       
        
         public Part(int partID, String name, int instock, int partMin, int partMax, double price) {
       //// public Part(int partID, String name, int instock, int partMin, int partMax, double price, String idName) {
            this.partID = new SimpleIntegerProperty(partID);
            this.name = new SimpleStringProperty(name);
            this.instock = new SimpleIntegerProperty(instock);
            this.partMin = new SimpleIntegerProperty(partMin);
            this.partMax = new SimpleIntegerProperty(partMax);
            this.price = new SimpleDoubleProperty(price);
            ////this.idName = new SimpleStringProperty(idName);//workstation or company name

            
        }
        
        public int getPartID() {
            return partID.get();
        }
 
        public void setPartID(int ID) {
            partID.set(ID);
        }

        public int getPartMin() {
            return partMin.get();
        }
 
        public void setPartMin(int min) {
            partMin.set(min);
        }
        
        public int getPartMax() {
            return partMax.get();
        }
 
        public void setPartMax(int max) {
            partMax.set(max);
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
 
        public double getPrice() {
            return price.get();
        }
 
        public void setPrice(double price) {
            this.price.set(price);
        }
//        public String getIDname() {
//            return idName.get();
//        }
// 
//        public void setIDname(String idName) {
//            this.idName.set(idName);
//        }

        
    }
        
