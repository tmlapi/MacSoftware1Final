package software1.finalsoftwarev2;

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

import software1.finalsoftwarev2.HelloApplication;

public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton addPartInhouseBtn;

    @FXML
    private RadioButton addPartOutsourceBtn;

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
        int id = HelloApplication.partIdCounter += 1;
        String name = partNameTxt.getText();
        int inv = Integer.parseInt(partInvTxt.getText());
        double price = Integer.parseInt(partPriceTxt.getText());
        int max = Integer.parseInt(partMaxTxt.getText());
        int min = Integer.parseInt(partMinTxt.getText());
        if (addPartInhouseBtn.isSelected()) {
            int machineId = Integer.parseInt(partMacIDCompNameTxt.getText());
        } else {
            String CompanyName = partMacIDCompNameTxt.getText();
        }

        System.out.println(id);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}