package software1.finalsoftwarev2;

import Model.Part;
import Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

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

    }

    @FXML
    void modifyProductSaveBtn(ActionEvent event) {

    }

    @FXML
    void modifyProductSearchBtn(ActionEvent event) {

    }

    public void sendProduct (Product product) {
        modifyProductIdTxt.setText(String.valueOf(product.getId()));
        modifyProductNameTxt.setText(product.getName());
        modifyProductInvTxt.setText(String.valueOf(product.getStock()));
        modifyProductPriceTxt.setText(String.valueOf(product.getPrice()));
        modifyProductMaxTxt.setText(String.valueOf(product.getMax()));
        modifyProductMinTxt.setText(String.valueOf(product.getMin()));
        // Still need to figure how to do radio buttons
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}