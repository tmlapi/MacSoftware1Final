package software1.finalsoftwarev2;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
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
    void onActionSaveNewPart(ActionEvent event) throws IOException {
        int id = HelloApplication.partIdCounter += 1;
        String name = partNameTxt.getText();
        int stock = Integer.parseInt(partInvTxt.getText());
        double price = Double.parseDouble(partPriceTxt.getText());
        int max = Integer.parseInt(partMaxTxt.getText());
        int min = Integer.parseInt(partMinTxt.getText());

        // Had to do this because system wasn't recognizing the variable since it was initilized in for loop
        int machineId = 0;
        String companyName = null;
        if (addPartInhouseBtn.isSelected()) {
            machineId = Integer.parseInt(partMacIDCompNameTxt.getText());
        } else {
            companyName = partMacIDCompNameTxt.getText();
        }

        // Saving outsource vs in house. Error i ran into here was that the variables weren't lining up with constructor
        //so i had to reorganize them
        if (addPartInhouseBtn.isSelected()) {
            Inventory.addPart(new InHouse(id, stock, min, max, name, price, machineId));
        } else {
            Inventory.addPart(new Outsourced(id, stock, min, max, name, price, companyName));
        }

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}