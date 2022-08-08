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

        Alert alert = new Alert(Alert.AlertType.WARNING, "Part Will Be Deleted, Press 'OK' To Continue!", ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Part deletePart = partTableView.getSelectionModel().getSelectedItem();
            if (deletePart == null) {
                return;
            } else {
                getAllParts().remove(deletePart);
            }
        } else if (result.get() == ButtonType.CANCEL) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "No Changes Made");
            alert1.show();
        }
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Product Will Be Deleted, Press 'OK' To Continue!", ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Product deleteProduct = productTableView.getSelectionModel().getSelectedItem();
            if (deleteProduct == null) {
                return;
            } else {
                getAllProducts().remove(deleteProduct);
            }
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPart.fxml"));
        loader.load();

        software1.finalsoftwarev2.ModifyPartsController MODController = loader.getController();
        MODController.sendPart(partTableView.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProduct.fxml"));
        loader.load();

        software1.finalsoftwarev2.ModifyProductController MODProController = loader.getController();
        MODProController.sendProduct(productTableView.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
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
    private ObservableList<Part> searchPartList (String name) {
        ObservableList<Part> filteredPartsList = FXCollections.observableArrayList();

        // I modified this using String.valueOf to convert the id into a string so it can be compared
        for (Part p : Inventory.getAllParts()) {
            if (p.getName().contains(name) || String.valueOf(p.getId()).contains(name)) {
                filteredPartsList.add(p);
            }
        }
        if (filteredPartsList.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "No Matches Found");
            alert1.show();
        }
        return filteredPartsList;
    }

    private ObservableList<Product> searchProductList (String name) {
        ObservableList<Product> filteredProductList = FXCollections.observableArrayList();

        // I modified this using String.valueOf to convert the id into a string so it can be compared
        for (Product p : Inventory.getAllProducts()) {
            if (p.getName().contains(name) || String.valueOf(p.getId()).contains(name)) {
                filteredProductList.add(p);
            }
        }
        if (filteredProductList.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "No Matches Found");
            alert1.show();
        }
        return filteredProductList;
    }


    @FXML
    public void partSearchBtn(ActionEvent event) {

        // This converts whatever is entered into the search bar into a string
        String searchResult = String.valueOf(partSearchTxt.getText());

        ObservableList<Part> filteredParts = searchPartList(searchResult);

        partTableView.setItems(filteredParts);
    }

    @FXML
    public void productSearchBtn(ActionEvent event) {

        // This converts whatever is entered into the search bar into a string
        String searchResult = String.valueOf(productSearchTxt.getText());

        ObservableList<Product> filteredProducts= searchProductList(searchResult);

        productTableView.setItems(filteredProducts);
    }
}