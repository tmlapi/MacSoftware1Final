package software1.finalsoftwarev2;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class AddProductController implements Initializable {

    public ObservableList<Part>;


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

    @FXML
    void addProductAddBtn(ActionEvent event) {

    }

    @FXML
    void addProductCancelBtn(ActionEvent event) {

    }

    @FXML
    void addProductRemoveBtn(ActionEvent event) {

    }

    @FXML
    void addProductSaveBtn(ActionEvent event) {

    }

    @FXML
    void addProductSearchBtn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductAvailablePartTableView.setItems(Inventory.getAllParts());
        addProductAssociatedPartTableView.setItems(Product.getAllAssociatedParts());
        );
    }
}