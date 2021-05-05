package ims;


import com.sun.xml.internal.ws.util.StringUtils;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IMSmain extends Application {
   
    
    
    public static void main(String[] args) {
        launch(args);
       
        
        
        
    }
 Scene sceneAdd, sceneProduct;
 Stage thestage;

               
 
 public static RadioButton rbInHouse;
 public static RadioButton rbOutsourced;
 public int partCount;
 public int prodCount;
 
    private final TableView<Part> tablePart = new TableView<>();
    private final TableView<Part> tablePartb = new TableView<>();
    private final TableView<Part> tablePartc = new TableView<>();
    private final TableView<Product> tableProduct = new TableView<>();
    private final TableView<partprod> tablepartprod = new TableView<>();
    
    
    // dummy table with temporary data...used for showing parts in a given product
    private final ObservableList<Part> dataPartc =
        FXCollections.<Part>observableArrayList(
            new inHouse (2, "Widget2", 25, 3, 100, 12.00, "VanIsleCorp")
        );
    
    private int rowIndex;
    private static Part selectedRecord;
    private static inHouse selectedRecordin;
    private static Outsourced selectedRecordout;
    //private static Outsourced selectedRecordout;
    private Part selectedRecord3;
    private Product selectedRecordProd;
    public Product selectedRecordpartprod;
    public Part sr;
    public Float pricetotal;
    public Boolean cleartext;
    private final TextField searchParts = new TextField();

    
    // ****************************************************
    // ************* Stage Starts Here ********************
    // ****************************************************
    @Override
    public void start(Stage primaryStage) {
          thestage = primaryStage;

        Scene sceneMain = new Scene(new Group());
        Scene sceneProduct = new Scene(new Group());
        thestage.setTitle("Inventory Management System");
        primaryStage.setWidth(930);
        primaryStage.setHeight(375);
 
        
        //== define labels and text fields======
        final Label label = new Label("Inventory Management System");
        label.setFont(new Font("Arial", 25));
        final Label partsLabel = new Label("Parts");
        final Label addPartLabel = new Label("Add Part");//Add Part form
        addPartLabel.setFont(new Font("Arial", 25));
        partsLabel.setFont(new Font("Arial", 20));
        final TextField idParts = new TextField();
        final Label productLabel = new Label("Products");
        productLabel.setFont(new Font("Arial", 20));
        TextField searchProducts = new TextField();
        
        // =====Parts Labels 
        final Label idPartsLabel = new Label("ID");
        final TextField nameParts = new TextField();
        final Label namePartsLabel = new Label("Name");
        final TextField instockParts = new TextField();
        final Label instockPartsLabel = new Label("Inventory");
        final TextField priceParts = new TextField();
        final Label pricePartsLabel = new Label("Price/Cost");
        
        final Label maxPartsLabel = new Label("Max");
        final TextField maxParts = new TextField();
        maxParts.setMaxWidth(60);
        final Label minPartsLabel = new Label("Min");
        TextField minParts = new TextField();
        minParts.setMaxWidth(60);
        final Label comPartsLabel = new Label("Company Name");
        final TextField comParts = new TextField();
        
        final Label modifyProductLabel = new Label("Modify Product");
        modifyProductLabel.setFont(new Font("Arial", 20));
        
        //=====Add/Modify Product Labels and text fields======
        final Label idProductLabel = new Label("ID");
        TextField idProduct = new TextField();
        final Label nameProductLabel = new Label("Name");
        TextField nameProduct = new TextField();
        final Label instockProductLabel = new Label("Inventory");
        TextField instockProduct = new TextField();
        final Label priceProductLabel = new Label("Price/Cost");
        TextField priceProduct = new TextField();
        final Label maxProductLabel = new Label("Max");
        TextField maxProduct = new TextField();
        maxProduct.setMaxWidth(60);
        final Label minProductLabel = new Label("Min");
        TextField minProduct = new TextField();
        minProduct.setMaxWidth(60);
        TextField searchModifyProduct = new TextField();//search field on add/modify product
        searchModifyProduct.setMaxWidth(160);
        int xb = 90;//button width
        int yb = 30;//button height
        

        
                //=====Radio Button Setup=====
        final ToggleGroup groupAddPart = new ToggleGroup();

        RadioButton rbInHouse = new RadioButton("In-House");
        rbInHouse.setToggleGroup(groupAddPart);
        rbInHouse.setSelected(false);
        
        RadioButton rbOutsourced = new RadioButton("Outsourced");
        rbOutsourced.setToggleGroup(groupAddPart);
        rbOutsourced.setSelected(true);
        //=====end radio button setup===========


        // ============================ 
        // ==== select from Tables ==== 
        // ============================ 
        
        // ====select from tableProduct=======
        tableProduct.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
            if (tableProduct.getSelectionModel().getSelectedItem() != null) {
                System.out.println("Selected item: " + newValue.toString());
                
            
            TableViewSelectionModel selectionModel = tableProduct.getSelectionModel();
            ObservableList selectedCells = selectionModel.getSelectedCells();
            TablePosition tablePosition = (TablePosition) selectedCells.get(0);
            this.rowIndex = tableProduct.getSelectionModel().getSelectedIndex();
            selectedRecordProd = (Product)tableProduct.getItems().get(rowIndex);
            for (int i=1; i<100; i++) {
               System.out.println("\b");
           }          
           System.out.println("tablePosition = " + tablePosition); 
           System.out.println("name = " + selectedRecordProd.getName());
           System.out.println("ID = " + selectedRecordProd.getProductID()); 
           System.out.println("------------------"); 

           idProduct.setText(String.valueOf(selectedRecordProd.getProductID()));
           nameProduct.setText(selectedRecordProd.getName());           
           instockProduct.setText(String.valueOf(selectedRecordProd.getInstock()));
           priceProduct.setText(String.valueOf(selectedRecordProd.getPrice()));
           maxProduct.setText(String.valueOf(selectedRecordProd.getMax()));
           minProduct.setText(String.valueOf(selectedRecordProd.getMin()));
           }
        });       
        
        // =======select from tablePart=======
        tablePart.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
            if (tablePart.getSelectionModel().getSelectedItem() != null) {
    
            TableViewSelectionModel selectionModel = tablePart.getSelectionModel();
            ObservableList selectedCells = selectionModel.getSelectedCells();
            TablePosition tablePosition = (TablePosition) selectedCells.get(0);
            this.rowIndex = tablePart.getSelectionModel().getSelectedIndex();
            selectedRecord = (Part)tablePart.getItems().get(rowIndex);
            //selectedRecordout = (Outsourced)tablePart.getItems().get(rowIndex);
            ////selectedRecord = (Part)tablePart.getItems().get(rowIndex);
           
           for (int i=1; i<100; i++) {
               System.out.println("\b");
           }
           System.out.println("tablePosition = " + tablePosition); 
           System.out.println("name = " + selectedRecord.getName());
           System.out.println("ID = " + selectedRecord.getPartID()); 
           System.out.println("------------------"); 
   
           idParts.setEditable(true);
           idParts.setText(String.valueOf(selectedRecord.getPartID()));
           nameParts.setText(selectedRecord.getName());           
           instockParts.setText(String.valueOf(selectedRecord.getInstock()));
           priceParts.setText(String.valueOf(selectedRecord.getPrice()));
           maxParts.setText(String.valueOf(selectedRecord.getPartMax()));
           minParts.setText(String.valueOf(selectedRecord.getPartMin()));
           
           // this only works for machineids, not companynames
           
           //TableColumn column = selectedCell.getTableColumn();
        //int rowIndex = selectedCell.getRow();
        //Object data = column.getCellObservableValue(rowIndex).getValue();
        //Object data = selectedRecord.getCellObservableValue(rowIndex).getValue();
           //String s = selectedRecord.getMachineID(); 
           
          
           
           String s = "" + Inventory.dataPart.get(rowIndex);
           char s1 = s.charAt(4);
           s = Character.toString(s1);
           
           
                if (s.equals("i")) {
                System.out.println("It is inHouse!");
                selectedRecordin = (inHouse)tablePart.getItems().get(rowIndex);
                comParts.setText(selectedRecordin.getMachineID());
                rbInHouse.setSelected(true); 
            } else  {
                System.out.println("It is Outsourced!"); 
                selectedRecordout = (Outsourced)tablePart.getItems().get(rowIndex);
                comParts.setText(selectedRecordout.getOutsourced());
                rbOutsourced.setSelected(true);
            }          
           }
        });       
        
        // =======select from tablePartb=======<---Notice this is table b 
        // ====================================    on the Product screen. 
        tablePartb.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
            if (tablePartb.getSelectionModel().getSelectedItem() != null) {
    
            TableViewSelectionModel selectionModel = tablePartb.getSelectionModel();
            ObservableList selectedCells = selectionModel.getSelectedCells();
            TablePosition tablePosition = (TablePosition) selectedCells.get(0);
            this.rowIndex = tablePartb.getSelectionModel().getSelectedIndex();
           
            selectedRecord = (Part)tablePartb.getItems().get(rowIndex);
            
           
           for (int i=1; i<100; i++) {
               System.out.println("\b");
           }
           System.out.println("tablePosition = " + tablePosition); 
           System.out.println("name = " + selectedRecord.getName());
           System.out.println("ID = " + selectedRecord.getPartID()); 
           System.out.println("------------------"); 
               
           }
        });
        
        
        
        // =======select from tablePartc=======<---Notice this is table c
        // ====================================    on the Product screen.
        tablePartc.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
            if (tablePartc.getSelectionModel().getSelectedItem() != null) {
    
            TableViewSelectionModel selectionModel = tablePartc.getSelectionModel();
            ObservableList selectedCells = selectionModel.getSelectedCells();
            TablePosition tablePosition = (TablePosition) selectedCells.get(0);
            this.rowIndex = tablePartc.getSelectionModel().getSelectedIndex();
            selectedRecord = (inHouse)tablePartc.getItems().get(rowIndex);
           
           for (int i=1; i<100; i++) {
               System.out.println("\b");
           }
           System.out.println("tablePosition = " + tablePosition); 
           System.out.println("name = " + selectedRecord.getName());
           System.out.println("ID = " + selectedRecord.getPartID()); 
           System.out.println("------------------"); 

           }
        });
        
        // forces numeric only ==maxParts field
    maxParts.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                maxParts.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
    
    // forces numeric only ==minParts field
    minParts.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                minParts.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
    
            
    // forces numeric only ==priceParts field
    priceParts.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d+(\\.\\d+)?")) {
                priceParts.setText(newValue.replaceAll("[^\\d+(\\.\\d+)?]", ""));
            }
        }
    });
    
    // forces numeric only ==instockParts field
    instockParts.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
             if (!newValue.matches("\\d*")) {
                instockParts.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
    
  
    // forces numeric only == maxProduct field
    maxProduct.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                maxProduct.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
    
    // forces numeric only == minProduct field
    minProduct.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                minProduct.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
    
            
    // forces numeric only == priceProduct field
    priceProduct.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d+(\\.\\d+)?")) {
                priceProduct.setText(newValue.replaceAll("[^\\d+(\\.\\d+)?]", ""));
            }
        }
    });
    
    // forces numeric only == instockProduct field
    instockProduct.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
             if (!newValue.matches("\\d*")) {
                instockProduct.setText(newValue.replaceAll("[^\\d]", ""));
            }
        }
    });
    
    
    
    // =========================
    
    
 
        //====Button Setup========
        
        
        // ===== SEARCH Part Button ==========
        Button searchPartbtn = new Button();
        searchPartbtn.setText("Search");
        searchPartbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            int test1 = Inventory.lookupPart(searchParts, tablePart);    
            
//            // Search functionality
//            String lowerCaseFilter = test1;
//            ObservableList<Part> subentriesPart = FXCollections.observableArrayList();
//            
//            tablePart.setItems(dataPart);
//            long count = tablePart.getColumns().stream().count();
//            for (int row = 0; row < tablePart.getItems().size(); row++) {
//                for (int col = 0; col < count; col++) {
//                    String entry = "" + tablePart.getColumns().get(col).getCellData(row);
//                    if (entry.toLowerCase().contains(lowerCaseFilter)) {
//                        subentriesPart.add(tablePart.getItems().get(row));
//                        break;
//                    }
//                }
//            }            
//            if (lowerCaseFilter != "") {
//              tablePart.setItems(subentriesPart);
//            }            
//            System.out.println("Searching for Part named " + lowerCaseFilter + ".");
            }
            });
        
        // ===== SEARCH Product Button ==========
        Button searchProductbtn = new Button();
        searchProductbtn.setText("Search");
        searchProductbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            String lowerCaseFilter = searchProducts.getText().toLowerCase();
            int test1 = Inventory.lookupProduct(lowerCaseFilter, tableProduct);  

            }
            });
        
        Button addPartbtn = new Button();
        addPartbtn.setText("Add");
        addPartbtn.setPrefSize(xb, yb);
        addPartbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            partCount = tablePart.getItems().size();
            System.out.println("Add Part!");
            partCount ++;
            
            idParts.setText(String.valueOf(partCount)); 
            idParts.setEditable(false); 
            nameParts.clear();
            instockParts.clear();
            priceParts.clear();
            maxParts.clear();
            minParts.clear();
            comParts.clear();
            primaryStage.setWidth(440);
            primaryStage.setHeight(420);
            addPartLabel.setText("Add Part");
            tablePart.setItems(Inventory.dataPart);
            primaryStage.setScene(sceneAdd);
            }
            });

            
        
        Button modifyPartbtn = new Button();
        modifyPartbtn.setText("Modify");
        modifyPartbtn.setPrefSize(xb, yb);
        modifyPartbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            int a = Inventory.updatePart(tablePart, idParts, rowIndex);

            primaryStage.setWidth(440);
            primaryStage.setHeight(420);
            addPartLabel.setText("Modify Part");
            primaryStage.setScene(sceneAdd);

            }
            });
        
        Button deletePartbtn = new Button();
        deletePartbtn.setText("Delete");
        deletePartbtn.setPrefSize(xb, yb);
        deletePartbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            int test1 = Inventory.deletePart(selectedRecord);

               
            
            
            }
            });
        
        Button addProductbtn = new Button();
        addProductbtn.setText("Add");
        addProductbtn.setPrefSize(xb, yb);
        addProductbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            prodCount = tableProduct.getItems().size();    
            System.out.println("Add Product! " + prodCount);
            prodCount ++;
            primaryStage.setWidth(930);
            primaryStage.setHeight(600);
            modifyProductLabel.setText("Add Product");
            idProduct.setText(String.valueOf(prodCount));
            
            nameProduct.clear();
            instockProduct.clear();
            priceProduct.clear();
            maxProduct.clear();
            minProduct.clear();
            tableProduct.setItems(ims.Inventory.dataProd); // clears any filtering and restores the product table
            
                
            primaryStage.setScene(sceneProduct);
            }
            });
        
        Button modifyProductbtn = new Button();
        modifyProductbtn.setText("Modify");
        modifyProductbtn.setPrefSize(xb, yb);
        modifyProductbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            System.out.println("Modify Product!");
            primaryStage.setWidth(930);
            primaryStage.setHeight(600);
            modifyProductLabel.setText("Modify Product");
            primaryStage.setScene(sceneProduct);
            int a = Inventory.updateProduct(tablePartc, dataPartc, tablepartprod,
                    idProduct, tablePartb, tableProduct);
            }
            });
        
        Button deleteProductbtn = new Button();
        deleteProductbtn.setText("Delete");
        deleteProductbtn.setPrefSize(xb, yb);
        deleteProductbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int test = Inventory.removeProduct(tablePartc, selectedRecordProd);
           
//           Alert alert = new Alert(AlertType.CONFIRMATION, "Delete this product? ", ButtonType.YES, ButtonType.CANCEL);
//            alert.showAndWait();
//
//            if (alert.getResult() == ButtonType.YES) {
//                if (tablePartc.getItems().size() >=0){
//                   Alert alert1 = new Alert(AlertType.ERROR);
//                    alert1.setTitle("Error Message");
//                    alert1.setHeaderText("Error message!");
//                    alert1.setContentText("This product contains at least one part and cannot be deleted!");
//                    alert1.showAndWait(); 
//                }
//                else { 
//                    Product.associatedParts.remove(selectedRecordProd);
//                    System.out.println("Delete Product!");
//                }
//            }     
            
            
            }
            }); 
        
        
        //====Add Part buttons=====
        Button btnSaveAddPart = new Button();
        btnSaveAddPart.setText("Save");
        btnSaveAddPart.setPrefSize(xb, yb);
        btnSaveAddPart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {  
//save part code starts here...
//*
             int test1 = Inventory.addPart(addPartLabel,instockParts, minParts, 
                maxParts, nameParts, comParts, idParts, priceParts,
                selectedRecord);
             if (test1 == 1) {
                thestage.setWidth(930);
                thestage.setHeight(375);
                thestage.setScene(sceneMain); 
             }
             
//save part code ends here

            } //ends handle actionevent
            });// ends event handler
              
            

        
        
        Button btnCancelAddPart = new Button();
        btnCancelAddPart.setText("Cancel");
        btnCancelAddPart.setPrefSize(xb, yb);
        btnCancelAddPart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {  
                
            Alert alert = new Alert(AlertType.CONFIRMATION, "Cancel this operation? ", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                   System.out.println("Cancel Saving Part!");
                    primaryStage.setWidth(930);
                    primaryStage.setHeight(375);
                    primaryStage.setScene(sceneMain);   
                }    
            }
           });
        
        //===================================
        //====Add Product screen buttons=====
        //===================================
        
        Button addProductbtn2 = new Button();
        addProductbtn2.setText("Add"); // add a part to a product
        addProductbtn2.setPrefSize(xb, yb);
        addProductbtn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            
            dataPartc.add(selectedRecord);

                
            System.out.println("Add Part to Product!");
            }
            });
        
        // add/modify product screen
        Button searchProductbtn2 = new Button();
        searchProductbtn2.setText("Search");
        searchProductbtn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            String lowerCaseFilter = searchModifyProduct.getText().toLowerCase();
            // Search functionality for add/modify product screen
            ObservableList<Part> subentriesPart2 = FXCollections.observableArrayList();
            
            tablePartb.setItems(Inventory.dataPart);
            long count = tablePartb.getColumns().stream().count();
            for (int row = 0; row < tablePartb.getItems().size(); row++) {
                for (int col = 0; col < count; col++) {
                    String entry = "" + tablePartb.getColumns().get(col).getCellData(row);
                    if (entry.toLowerCase().contains(lowerCaseFilter)) {
                        subentriesPart2.add(tablePartb.getItems().get(row));
                        break;
                    }
                }
            }            
            if (lowerCaseFilter != "") {
              tablePartb.setItems(subentriesPart2);
            }            
            System.out.println("Searching for Part named " + lowerCaseFilter + ".");
            }
            });
        
        Button deleteProductbtn2 = new Button();
        deleteProductbtn2.setText("Delete");
        deleteProductbtn2.setPrefSize(xb, yb);
        deleteProductbtn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            dataPartc.remove(selectedRecord);
            System.out.println("Delete Part from Product!");
            tablePartc.setItems(dataPartc);
            }
            });
        
        Button saveProductbtn2 = new Button();
        saveProductbtn2.setText("Save");
        saveProductbtn2.setPrefSize(xb, yb);
        saveProductbtn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            String selectedProdID = idProduct.getText();
            int test1 = Inventory.addProduct(pricetotal, tablePartc, priceProduct,
                    nameProduct,instockProduct, maxProduct, minProduct,modifyProductLabel,
                    Inventory.dataProd, idProduct, tablepartprod, selectedRecordProd,
                    Product.associatedParts);
            if (test1 == 1) {
                primaryStage.setWidth(930);
                primaryStage.setHeight(375);
                primaryStage.setScene(sceneMain);
            }
            
            

            }
});
        
        Button cancelProductbtn2 = new Button();
        cancelProductbtn2.setText("Cancel");
        cancelProductbtn2.setPrefSize(xb, yb);
        cancelProductbtn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            Alert alert = new Alert(AlertType.CONFIRMATION, "Cancel this operation? ", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                   System.out.println("Cancel Modifying Product!");
                   primaryStage.setWidth(930);
                   primaryStage.setHeight(375);
                   primaryStage.setScene(sceneMain);  
                } 
            }
          });
        
        //=====end button setup=======
        


        
        //======Add Part Radio Button Listener=========
        rbInHouse.selectedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
            public void changed(ObservableValue<? extends Boolean> obs, 
                    Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) { 
                    // set machineID
                    int test = inHouse.setMachineID(comPartsLabel, comParts);                    
                } else {
                    // set company name
                    int test = Outsourced.setCompanyName(comPartsLabel, comParts);
                }
            }   
            });
        //======END - Add Part Radio Button Listener=========
        
        
// Begin part table creation and setup - includes copy of table called tablePartb
// to go in the add product and modify product screens...same data in each.
        
        tablePart.setEditable(true);
        tablePart.setMaxHeight(100);
        tablePart.setMaxWidth(390);
        
        
        tablePartb.setEditable(true);
        tablePartb.setMaxHeight(100);
        tablePartb.setMaxWidth(390);
        
        tablePartc.setEditable(true);
        tablePartc.setMaxHeight(100);
        tablePartc.setMaxWidth(390);
 
        // tablePart
        TableColumn partIDCol = new TableColumn("PartID");
        partIDCol.setMinWidth(50);
        partIDCol.setCellValueFactory(
                new PropertyValueFactory<>("partID"));
 
        TableColumn nameCol = new TableColumn("Part Name");
        nameCol.setMinWidth(80);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
 
        TableColumn instockCol = new TableColumn("Inventory Level");
        instockCol.setMinWidth(100);
        instockCol.setCellValueFactory(
                new PropertyValueFactory<>("instock"));
        
        TableColumn priceCol = new TableColumn("Price/Cost per Unit");
        priceCol.setMinWidth(120);
        priceCol.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        
        
        // tablePartb
        TableColumn partIDColb = new TableColumn("PartID");
        partIDColb.setMinWidth(50);
        partIDColb.setCellValueFactory(
                new PropertyValueFactory<>("partID"));
 
        TableColumn nameColb = new TableColumn("Part Name");
        nameColb.setMinWidth(80);
        nameColb.setCellValueFactory(
                new PropertyValueFactory<>("name"));
 
        TableColumn instockColb = new TableColumn("Inventory Level");
        instockColb.setMinWidth(100);
        instockColb.setCellValueFactory(
                new PropertyValueFactory<>("instock"));
        
        TableColumn priceColb = new TableColumn("Price/Cost per Unit");
        priceColb.setMinWidth(120);
        priceColb.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        
        //===tablepartc====
        
        TableColumn partIDColc = new TableColumn("PartID");
        partIDColc.setMinWidth(50);
        partIDColc.setCellValueFactory(
                new PropertyValueFactory<>("partID"));
 
        TableColumn nameColc = new TableColumn("Part Name");
        nameColc.setMinWidth(80);
        nameColc.setCellValueFactory(
                new PropertyValueFactory<>("name"));
 
        TableColumn instockColc = new TableColumn("Inventory Level");
        instockColc.setMinWidth(100);
        instockColc.setCellValueFactory(
                new PropertyValueFactory<>("instock"));
        
        TableColumn priceColc = new TableColumn("Price/Cost per Unit");
        priceColc.setMinWidth(120);
        priceColc.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        
        //===tablepartprod====
        
        TableColumn idofprodCol = new TableColumn("ProdID");
        idofprodCol.setMinWidth(50);
        idofprodCol.setCellValueFactory(
                new PropertyValueFactory<>("idofprod"));
 
        TableColumn idofpartCol = new TableColumn("PartID");
        idofpartCol.setMinWidth(80);
        idofpartCol.setCellValueFactory(
                new PropertyValueFactory<>("idofpart"));
        
   
        tablePart.setItems(Inventory.dataPart);
        tablePart.getColumns().addAll(partIDCol, nameCol, instockCol, priceCol);
        tablePartb.setItems(Inventory.dataPart);
        tablePartb.getColumns().addAll(partIDColb, nameColb, instockColb, priceColb);
        
       
        // tablePartc is for PARTS that exist within a PRODUCT
        // 
        tablePartc.setItems(dataPartc);
        tablePartc.getColumns().addAll(partIDColc, nameColc, instockColc, priceColc);
        tablepartprod.setItems(Product.associatedParts);
        tablepartprod.getColumns().addAll(idofprodCol, idofpartCol);
        // end part table setup
        // ====================
 
 
 // Begin Product table creation and setup      
        
        tableProduct.setEditable(true);
        tableProduct.setMaxHeight(100);
        tableProduct.setMinWidth(390);
 
        TableColumn productIDCol = new TableColumn("ProductID");
        productIDCol.setMinWidth(50);
        productIDCol.setCellValueFactory(
                new PropertyValueFactory<>("productID"));
 
        TableColumn prodNameCol = new TableColumn("Product Name");
        prodNameCol.setMinWidth(80);
        prodNameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
 
        TableColumn instockProdCol = new TableColumn("Inventory Level");
        instockProdCol.setMinWidth(100);
        instockProdCol.setCellValueFactory(
                new PropertyValueFactory<>("instock"));
        
        TableColumn prodPriceCol = new TableColumn("Price per Unit");
        prodPriceCol.setMinWidth(100);
        prodPriceCol.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        
        
        //tableProduct.setItems(sortedDataProd);
        tableProduct.setItems(Inventory.dataProd);
        tableProduct.getColumns().addAll(productIDCol, prodNameCol, instockProdCol, prodPriceCol);
 // ======= end product table setup=====
 // ====================================
 

 
 // ===Setup parts and product panes====
        final Pane titlePane = new Pane();
        titlePane.setLayoutX(20);
        titlePane.setLayoutX(30);
        label.setLayoutX(0);
        label.setLayoutY(20);
                
        final Pane partsPane = new Pane();
        partsPane.setLayoutX(20);
        partsPane.setLayoutY(70);
        partsPane.setPadding(new Insets(10, 10, 10, 10));
        partsPane.setStyle("-fx-border-color: black;-fx-border-radius:10;");
        searchPartbtn.setLayoutX(180);
        searchPartbtn.setLayoutY(10);
        searchParts.setLayoutX(250);
        searchParts.setLayoutY(10);
        partsLabel.setLayoutX(20);
        partsLabel.setLayoutY(10);
        tablePart.setLayoutX(20);
        tablePart.setLayoutY(50);
        addPartbtn.setLayoutX(70);
        addPartbtn.setLayoutY(160);
        modifyPartbtn.setLayoutX(190);
        modifyPartbtn.setLayoutY(160);
        deletePartbtn.setLayoutX(310);
        deletePartbtn.setLayoutY(160);
        //=====
        
        final Pane productPane = new Pane();
        productPane.setLayoutX(455);
        productPane.setLayoutY(70);
        productPane.setPadding(new Insets(10, 10, 10, 10));
        productPane.setStyle("-fx-border-color: black;-fx-border-radius:10;");
        searchProductbtn.setLayoutX(190);
        searchProductbtn.setLayoutY(10);
        searchProducts.setLayoutX(260);
        searchProducts.setLayoutY(10);
        productLabel.setLayoutX(20);
        productLabel.setLayoutY(10);
        tableProduct.setLayoutX(20);
        tableProduct.setLayoutY(50);
        addProductbtn.setLayoutX(80);
        addProductbtn.setLayoutY(160);
        modifyProductbtn.setLayoutX(200);
        modifyProductbtn.setLayoutY(160);
        deleteProductbtn.setLayoutX(320);
        deleteProductbtn.setLayoutY(160);
        //====
        
        //==== setup Add/Modify Parts Pane ===
        final Pane addPartsPane = new Pane();
        addPartsPane.setPadding(new Insets(10, 10, 10, 10));
        addPartLabel.setLayoutX(10);
        addPartLabel.setLayoutY(20);
        btnSaveAddPart.setLayoutX(200);
        btnSaveAddPart.setLayoutY(325);
        btnCancelAddPart.setLayoutX(320);
        btnCancelAddPart.setLayoutY(325);
        //----
        rbInHouse.setLayoutX(160);
        rbInHouse.setLayoutY(30);
        rbOutsourced.setLayoutX(270);
        rbOutsourced.setLayoutY(30);
        idPartsLabel.setLayoutX(70);
        idPartsLabel.setLayoutY(80);
        idParts.setLayoutX(170);
        idParts.setLayoutY(80-3);
        idParts.setStyle("-fx-background-color: grey;-fx-text-inner-color: yellow;");
        idParts.setEditable(true);
        namePartsLabel.setLayoutX(70); 
        namePartsLabel.setLayoutY(120);
        nameParts.setLayoutX(170);
        nameParts.setLayoutY(120);
        instockPartsLabel.setLayoutX(70);
        instockPartsLabel.setLayoutY(160);          
        instockParts.setLayoutX(170); 
        instockParts.setLayoutY(160);
        //
        pricePartsLabel.setLayoutX(70);
        pricePartsLabel.setLayoutY(200);
        priceParts.setLayoutX(170); 
        priceParts.setLayoutY(200);
        maxPartsLabel.setLayoutX(70);
        maxPartsLabel.setLayoutY(240);
        maxParts.setLayoutX(170);
        maxParts.setLayoutY(240);
        minPartsLabel.setLayoutX(260);
        minPartsLabel.setLayoutY(240);        
        minParts.setLayoutX(290);
        minParts.setLayoutY(240);
        //
        comPartsLabel.setLayoutX(70);
        comPartsLabel.setLayoutY(280);
        comParts.setLayoutX(170);
        comParts.setLayoutY(280);
        
        //==== setup Add/Modify Product window ===
        int x = 30; 
        int x1 = x + 100;
        int y =130;
        final Pane modifyProductPane = new Pane();
        modifyProductPane.setPrefSize(875,520);
        modifyProductPane.setMaxSize(875,520);
        modifyProductPane.setMinSize(875,520);
        modifyProductPane.setLayoutX(20);
        modifyProductPane.setLayoutY(20);
        modifyProductPane.setPadding(new Insets(10, 10, 10, 10));
        modifyProductPane.setStyle("-fx-border-color: black;-fx-border-radius:10;");
        modifyProductLabel.setLayoutX(20);
        modifyProductLabel.setLayoutY(70);
        idProductLabel.setLayoutX(x);
        idProductLabel.setLayoutY(y);
        idProduct.setLayoutX(x1);
        idProduct.setLayoutY(y-3);
        idProduct.setEditable(false);
        idProduct.setStyle("-fx-background-color: grey;-fx-text-inner-color: yellow;");
        y = y + 40;
        nameProductLabel.setLayoutX(x); 
        nameProductLabel.setLayoutY(y);
        nameProduct.setLayoutX(x1);
        nameProduct.setLayoutY(y-3);
        y = y + 40;
        instockProductLabel.setLayoutX(x);
        instockProductLabel.setLayoutY(y-3);          
        instockProduct.setLayoutX(x1); 
        instockProduct.setLayoutY(y-3);
        y = y + 40;
        priceProductLabel.setLayoutX(x);
        priceProductLabel.setLayoutY(y-3);
        priceProduct.setLayoutX(x1); 
        priceProduct.setLayoutY(y-3);
        y = y + 40;
        maxProductLabel.setLayoutX(x);
        maxProductLabel.setLayoutY(y-3);
        maxProduct.setLayoutX(x1);
        maxProduct.setLayoutY(y-3);
        minProductLabel.setLayoutX(x+185);
        minProductLabel.setLayoutY(y-3);        
        minProduct.setLayoutX(x+220);
        minProduct.setLayoutY(y-3);
        saveProductbtn2.setLayoutX(650);
        saveProductbtn2.setLayoutY(475);
        searchProductbtn2.setLayoutX(550);
        searchProductbtn2.setLayoutY(70);
        searchModifyProduct.setLayoutX(610);
        searchModifyProduct.setLayoutY(70);
        tablePartb.setLayoutX(470);
        tablePartb.setLayoutY(105);
        tablePartc.setLayoutX(470);
        tablePartc.setLayoutY(295);
        addProductbtn2.setLayoutX(750);
        addProductbtn2.setLayoutY(220);
        cancelProductbtn2.setLayoutX(750);
        cancelProductbtn2.setLayoutY(475);
        deleteProductbtn2.setLayoutX(750);
        deleteProductbtn2.setLayoutY(430);
        int a[] = new int[]{1,2};
        //partprodList.add(new partprod(1,4));
        
        //
        
        // clears the partc table
        
        dataPartc.remove(0);
        
        
        //
        
        
        
        // === bring the buttons, fields, etc into panes and scenes
        //main window starts here
        
        productPane.getChildren().addAll( productLabel, searchProducts, 
                searchProductbtn, tableProduct, addProductbtn, modifyProductbtn, 
                deleteProductbtn);
        //        
        partsPane.getChildren().addAll( partsLabel, searchParts, 
                searchPartbtn, tablePart, addPartbtn, modifyPartbtn, deletePartbtn);
        
        //

        titlePane.getChildren().addAll( label);
        //

        ////main window ends here
        addPartsPane.getChildren().addAll( rbInHouse,rbOutsourced, addPartLabel, btnSaveAddPart, btnCancelAddPart,
                idPartsLabel, idParts, namePartsLabel, nameParts, instockPartsLabel, instockParts,
                pricePartsLabel,priceParts, maxPartsLabel, maxParts, minPartsLabel, minParts,
                comPartsLabel, comParts);
        //
        modifyProductPane.getChildren().addAll(modifyProductLabel,idProductLabel,idProduct,
                nameProductLabel,nameProduct,instockProductLabel,instockProduct,
                priceProductLabel,priceProduct,maxProductLabel,maxProduct,minProductLabel,
                minProduct, saveProductbtn2, searchProductbtn2, searchModifyProduct, tablePartb, tablePartc, 
                addProductbtn2, cancelProductbtn2, deleteProductbtn2);
        //
        ((Group) sceneMain.getRoot()).getChildren().addAll(titlePane, productPane, partsPane);
        ((Group) sceneProduct.getRoot()).getChildren().addAll(modifyProductPane);
        //
        sceneAdd = new Scene(addPartsPane, 20, 20);
        
        primaryStage.setScene(sceneMain);
        
        primaryStage.show();
        
    }

    
} 