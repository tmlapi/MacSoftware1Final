package software1.finalsoftwarev2;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static Model.Inventory.getAllParts;
import static Model.Inventory.getAllProducts;

public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partPriceUnitCol;

    @FXML
    private TextField partSearchTxt;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, Integer> productInventoryCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Double> productPriceUnitCol;

    @FXML
    private TextField productSearchTxt;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        Part deletePart = partTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Part Will Be Deleted, Press 'OK' To Continue!", ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deletePart(deletePart);
        } else if (result.get() == ButtonType.CANCEL) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "No Changes Made");
            alert1.show();
        }
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Product deleteProduct = productTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Product Will Be Deleted, Press 'OK' To Continue!", ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deleteProduct(deleteProduct);
        } else if (result.get() == ButtonType.CANCEL) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "No Changes Made");
            alert1.show();
        }
    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyPart.fxml"));
            loader.load();

            software1.finalsoftwarev2.ModifyPartsController MODController = loader.getController();
            int partIndex = partTableView.getSelectionModel().getSelectedIndex();
            Part SP = partTableView.getSelectionModel().getSelectedItem();


            // To make this work for the SubClasses of Part, i had to create two new methods one for Inhouse and
            //one for outsourced. Then, after running through a IF statement, was able to correctly send right stuff
            // the big issue I also had here was sending the partIndex. What i did was, i made an 'int index' as part of the
            //parameter for my send methods. I did this because there was no way to access it from the ModifyPartsController
            //on its own. By making it part of the send method, and calling the send method within the MainScreen, i was able
            // to pass the index to ModifyParts.fxml when it opened up ModifyParts, part of the Send method, sets a local
            //index value based on what was selected (partTableView.getSelectionModel().getSelectedItem()) on the MainScreen
            // This sets the current index to whatever was selected before hitting 'modify' on the main screen.
            if (SP instanceof InHouse) {
                MODController.sendInhousePart(partIndex, SP);

            } else {
                MODController.sendOutsourcedPart(partIndex, SP);
            }

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select a Part First!");
            alert.show();
        }
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyProducts.fxml"));
            loader.load();

            int partIndex = productTableView.getSelectionModel().getSelectedIndex();
            Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

            software1.finalsoftwarev2.ModifyProductController MODProController = loader.getController();
            MODProController.sendProduct(partIndex, selectedProduct);

            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select a Product First!");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Setting the table view in MainScreen for Parts and Product tables using the getter for observable lists
        //in the Inventory model
        partTableView.setItems(getAllParts());
        productTableView.setItems(Inventory.getAllProducts());

        // Setting the part cols to the associated values
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Setting the product cols to the associated values
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    // Search/Filtered List
    @FXML
    public void partSearchBtn(ActionEvent event) {

        // This converts whatever is entered into the search bar into a string
        String searchResult = String.valueOf(partSearchTxt.getText());

        ObservableList<Part> filteredParts = Inventory.lookupPart(searchResult);

        partTableView.setItems(filteredParts);
    }

    @FXML
    public void productSearchBtn(ActionEvent event) {

        // This converts whatever is entered into the search bar into a string
        String searchResult = String.valueOf(productSearchTxt.getText());

        ObservableList<Product> filteredProducts= Inventory.lookupProduct(searchResult);

        productTableView.setItems(filteredProducts);
    }
}