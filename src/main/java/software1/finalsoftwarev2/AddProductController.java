package software1.finalsoftwarev2;

import Model.Inventory;
import Model.Part;
import Model.Product;
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

/**
 * THis is the Add Product class.
 */
public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;

    public ObservableList<Part> allAssociatedParts = FXCollections.observableArrayList();


    @FXML
    private TableColumn<Part, Integer> addProductAssociatedPartIdCol;

    @FXML
    private TableColumn<Part, Integer> addProductAssociatedPartInvCol;

    @FXML
    private TableColumn<Part, String> addProductAssociatedPartNameCol;

    @FXML
    private TableColumn<Part, Double> addProductAssociatedPartPriceCol;

    @FXML
    private TableView<Part> addProductAssociatedPartTableView;

    @FXML
    private TableColumn<Part, Integer> addProductAvailablePartIdCol;

    @FXML
    private TableColumn<Part, Integer> addProductAvailablePartInvCol;

    @FXML
    private TableColumn<Part, String> addProductAvailablePartNameCol;

    @FXML
    private TableColumn<Part, Double> addProductAvailablePartPriceCol;

    @FXML
    private TableView<Part> addProductAvailablePartTableView;

    @FXML
    private TextField addProductIdTxt;

    @FXML
    private TextField addProductInvTxt;

    @FXML
    private TextField addProductMaxTxt;

    @FXML
    private TextField addProductMinTxt;

    @FXML
    private TextField addProductNameTxt;

    @FXML
    private TextField addProductPriceTxt;

    @FXML
    private TextField addProductSearch;

    /**
     * Add Product button.
     * This is the button that adds the product to the table as well as the product list
     * @param event ActionEvent
     */
    @FXML
    void addProductAddBtn(ActionEvent event) {
        Part SP = addProductAvailablePartTableView.getSelectionModel().getSelectedItem();

        if (SP == null) {
            return;
        } else if (!allAssociatedParts.contains(SP)) {
                allAssociatedParts.add(SP);
                addProductAssociatedPartTableView.setItems(allAssociatedParts);
        }
    }

    /**
     * Add Proudct Cancel Button.
     * This is the button that allows the user to  cancel the addition of a new product and go back to main screen.
     * @param event ActionEvent
     */
    @FXML
    void addProductCancelBtn(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Part removal button for associated parts.
     * This is the button that allows you to remove associated parts from a product.
     * @param event ActionEvent
     */
    @FXML
    void addProductRemoveBtn(ActionEvent event) {
        Part deleteAssociatedPart = addProductAssociatedPartTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Product Will Be Deleted, Press 'OK' To Continue!", ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            allAssociatedParts.remove(deleteAssociatedPart);
        } else if (result.get() == ButtonType.CANCEL) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "No Changes Made");
            alert1.show();
        }

    }

    // Because it is not static, i need an instance of Product to access the methods within Product. Once i am able to
    //creat those, i can access them and add the mto all associated parts!
    //Example Product p = new Product(1, 3, 1, 4, "Bike", 32.32);

    /**
     * Save New Product Button.
     * This is the action event that allows you to save a new button, assigns the new products to associated list and assigns
     * associated parts to associated list for that product. Also does logistical tests.
     * @param event
     * @throws IOException
     */
    @FXML
    void addProductSaveBtn(ActionEvent event) throws IOException {

        try {
            int id = Main.productIdCounter += 1;
            int stock = Integer.parseInt(addProductInvTxt.getText());
            int min = Integer.parseInt(addProductMinTxt.getText());
            String name = addProductNameTxt.getText();
            int max = Integer.parseInt(addProductMaxTxt.getText());
            double price = Double.parseDouble(addProductPriceTxt.getText());

            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Min Must Be Less Than Max!");
                alert.showAndWait();
            } else if (stock < min || stock > max ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Inventory Must Be Between Min and Max!");
                alert.showAndWait();
            } else {

                Product p = new Product(id, stock, min, max, name, price);
                p.getAllAssociatedParts().addAll(allAssociatedParts);
                Inventory.getAllProducts().add(p);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Correct Field Values, Please!");
            alert.show();
        }

    }

    /**
     * Part search button.
     * Allows you to search for abailable parts by name or id. Then with filter the table to reflect so.
     * @param event ActionEvent
     */
    @FXML
    void addProductSearchBtn(ActionEvent event) {
        String searchResult = String.valueOf(addProductSearch.getText());

        ObservableList<Part> filteredParts = Inventory.lookupPart(searchResult);

        addProductAvailablePartTableView.setItems(filteredParts);
    }

    /**
     * Main initializer for AddProduct screen.
     * Sets up the tables accordingly.
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductAvailablePartTableView.setItems(Inventory.getAllParts());
        addProductAssociatedPartTableView.setItems(allAssociatedParts);

        addProductAvailablePartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductAvailablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductAvailablePartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductAvailablePartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProductAssociatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductAssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductAssociatedPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductAssociatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}

