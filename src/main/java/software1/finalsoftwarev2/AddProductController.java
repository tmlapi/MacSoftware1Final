package software1.finalsoftwarev2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {


    @FXML
    private TableColumn<?, ?> addProductAssociatedPartIdCol;

    @FXML
    private TableColumn<?, ?> addProductAssociatedPartInvCol;

    @FXML
    private TableColumn<?, ?> addProductAssociatedPartNameCol;

    @FXML
    private TableColumn<?, ?> addProductAssociatedPartPriceCol;

    @FXML
    private TableView<?> addProductAssociatedPartTableView;

    @FXML
    private TableColumn<?, ?> addProductAvailablePartIdCol;

    @FXML
    private TableColumn<?, ?> addProductAvailablePartInvCol;

    @FXML
    private TableColumn<?, ?> addProductAvailablePartNameCol;

    @FXML
    private TableColumn<?, ?> addProductAvailablePartPriceCol;

    @FXML
    private TableView<?> addProductAvailablePartTableView;

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

    }
}