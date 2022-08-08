package software1.finalsoftwarev2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private ToggleGroup bTogGroup;

    @FXML
    private Label machineCompanyLbl;

    @FXML
    private TextField partIdTxt;

    @FXML
    private TextField partInvTxt;

    @FXML
    private TextField partMacIDCompNameTxt;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private TextField partMinTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    void inhouseRadio(ActionEvent event) {
        machineCompanyLbl.setText("Machine ID");
    }

    @FXML
    void outsourcedRadio(ActionEvent event) {
        machineCompanyLbl.setText("Company Name");
    }

    @FXML
    void onActionCancelAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSaveNewPart(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}