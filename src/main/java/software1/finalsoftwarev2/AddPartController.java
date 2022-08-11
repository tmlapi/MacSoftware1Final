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

/**
 * Main AddParts Contorller. This is the main class that gives action to the AddParts fxml.
 */
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

    /**
     * Inhouse radio button.
     * This is the radio button that gives the form the Machine ID txt field.
     * @param event ActionEvent
     */
    @FXML
    void inhouseRadio(ActionEvent event) {
        machineCompanyLbl.setText("Machine ID");
    }

    /**
     * Outsourced radio button.
     * This is the radio buton that gives the form the Company Name Txt field.
     * @param event ActionEvent
     */
    @FXML
    void outsourcedRadio(ActionEvent event) {
        machineCompanyLbl.setText("Company Name");
    }

    /**
     * Cancel Button.
     * This is the cancel button that goes back to main screen if user doesn't want to continue with adding the part.
     * @param event ActionEvent
     */
    @FXML
    void onActionCancelAddPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Save New Part Button.
     * This is the button that allows you to save new part and goes through logistical checks in the process. It then checks
     * the radio button and creates a new correlated part using addPart method.
     * @param event ActionEvent
     */
    @FXML
    void onActionSaveNewPart(ActionEvent event) throws IOException {
        try {
            int id = Main.partIdCounter += 1;
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

            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Min Must Be Less Than Max!");
                alert.showAndWait();
            } else if (stock < min || stock > max ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Inventory Must Be Between Min and Max!");
                alert.showAndWait();
            } else {
                // Saving outsource vs in house. Error i ran into here was that the variables weren't lining up with constructor
                //so i had to reorganize them
                if (addPartInhouseBtn.isSelected()) {
                    Inventory.addPart(new InHouse(id, stock, min, max, name, price, machineId));
                } else {
                    Inventory.addPart(new Outsourced(id, stock, min, max, name, price, companyName));
                }

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Proper Field Values, Please!");
            alert.show();
        }
    }

    /**
     * Main initialize method.
     * This is the main method for the addpart controller.
     * @param url url
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}