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

public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

    public ObservableList<Part> allAssociatedParts = FXCollections.observableArrayList();
    int currentIndex = 0;

    Product selectedProduct;
    @FXML
    private TableColumn<Part, Integer> modifyProductAssociatedPartIdCol;

    @FXML
    private TableColumn<Part, Integer> modifyProductAssociatedPartInvCol;

    @FXML
    private TableColumn<Part, String> modifyProductAssociatedPartNameCol;

    @FXML
    private TableColumn<Part, Double> modifyProductAssociatedPartPriceCol;

    @FXML
    private TableView<Part> modifyProductAssociatedPartTableView;

    @FXML
    private TableColumn<Part, Integer> modifyProductAvailablePartIdCol;

    @FXML
    private TableColumn<Part, Integer> modifyProductAvailablePartInvCol;

    @FXML
    private TableColumn<Part, String> modifyProductAvailablePartNameCol;

    @FXML
    private TableColumn<Part, Double> modifyProductAvailablePartPriceCol;

    @FXML
    private TableView<Part> modifyProductAvailablePartTableView;

    @FXML
    private TextField modifyProductIdTxt;

    @FXML
    private TextField modifyProductInvTxt;

    @FXML
    private TextField modifyProductMaxTxt;

    @FXML
    private TextField modifyProductMinTxt;

    @FXML
    private TextField modifyProductNameTxt;

    @FXML
    private TextField modifyProductPriceTxt;

    @FXML
    private TextField modifyProductSearch;

    @FXML
    void modifyProductAddBtn(ActionEvent event) {

        Part SP = modifyProductAvailablePartTableView.getSelectionModel().getSelectedItem();

        if (SP == null) {
            return;
        } else if (!allAssociatedParts.contains(SP)) {
            allAssociatedParts.add(SP);
            modifyProductAssociatedPartTableView.setItems(allAssociatedParts);
        }

    }

    @FXML
    void modifyProductCancelBtn(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void modifyProductRemoveBtn(ActionEvent event) {
        Part deleteAssociatedPart = modifyProductAssociatedPartTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Product Will Be Deleted, Press 'OK' To Continue!", ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            allAssociatedParts.remove(deleteAssociatedPart);

            //This might need to be on the save button as if left the modifypfroduct screen,
            //the part got removed anyway...
            /*selectedProduct.getAllAssociatedParts().setAll(allAssociatedParts);*/
        } else if (result.get() == ButtonType.CANCEL) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "No Changes Made");
            alert1.show();
        }
    }

    @FXML
    void modifyProductSaveBtn(ActionEvent event) {
        try {

            int id = Integer.parseInt(modifyProductIdTxt.getText());
            int stock = Integer.parseInt(modifyProductInvTxt.getText());
            int min = Integer.parseInt(modifyProductMinTxt.getText());
            String name = modifyProductNameTxt.getText();
            int max = Integer.parseInt(modifyProductMaxTxt.getText());
            double price = Double.parseDouble(modifyProductPriceTxt.getText());

            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Min Must Be Less Than Max!");
                alert.show();
            } else if (stock < min || stock > max ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Inventory Must Be Between Min and Max!");
                alert.show();
            } else {
                Product updatedProduct = new Product(id, stock, min, max, name, price);
                Inventory.updateProduct(currentIndex, updatedProduct);

                //This takes our allAssociatedParts table and compares it to the associatedparts table. If a part isn't in the
                //associatedparts table within the Product class, it adds it
                for (Part part : allAssociatedParts) {
                    if (!updatedProduct.getAllAssociatedParts().contains(part)) {
                        updatedProduct.addAssociattedPart(part);
                    }
                }

                // THis is run to compare the local associated parts table with the associated parts table within the Product class
                //if it finds that a part is contained in the product class one and NOT in the local one (because we removed it
                //earlier with the button, it goes ahead and deletes it form the product associated table one
                for (Part part : updatedProduct.getAllAssociatedParts()) {
                    if (updatedProduct.getAllAssociatedParts().contains(part) && !allAssociatedParts.contains(part)) {
                        updatedProduct.deleteAssociatedPart(part);
                    }
                }

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch (NumberFormatException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Proper Field Values, Please!");
            alert.show();
        }

    }

    @FXML
    void modifyProductSearchBtn(ActionEvent event) {

        String searchResult = String.valueOf(modifyProductSearch.getText());

        ObservableList<Part> filteredParts = Inventory.lookupPart(searchResult);

        modifyProductAvailablePartTableView.setItems(filteredParts);
    }

    public void sendProduct (int index, Product product) {
        selectedProduct = product;
        currentIndex = index;

        modifyProductIdTxt.setText(String.valueOf(product.getId()));
        modifyProductNameTxt.setText(product.getName());
        modifyProductInvTxt.setText(String.valueOf(product.getStock()));
        modifyProductPriceTxt.setText(String.valueOf(product.getPrice()));
        modifyProductMaxTxt.setText(String.valueOf(product.getMax()));
        modifyProductMinTxt.setText(String.valueOf(product.getMin()));

        modifyProductAvailablePartTableView.setItems(Inventory.getAllParts());
        modifyProductAssociatedPartTableView.setItems(allAssociatedParts);

        modifyProductAvailablePartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductAvailablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductAvailablePartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductAvailablePartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyProductAssociatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductAssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductAssociatedPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductAssociatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        for (Part part: product.getAllAssociatedParts()) {
            allAssociatedParts.add(part);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}