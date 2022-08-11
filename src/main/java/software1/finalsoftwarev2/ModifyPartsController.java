package software1.finalsoftwarev2;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
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
 * The controller and methods for Modifying parts.
 */
public class ModifyPartsController implements Initializable {

    Stage stage;
    Parent scene;

    int currentIndex = 0;

    @FXML
    private RadioButton modifyInhouseBtn;

    @FXML
    private RadioButton modifyOutsourceBtn;

    @FXML
    private Label modifyMachineCompanyLbl;

    @FXML
    private TextField modifyPartIdTxt;

    @FXML
    private TextField modifyPartInvTxt;

    @FXML
    private TextField modifyPartMacIDCompNameTxt;

    @FXML
    private TextField modifyPartMaxTxt;

    @FXML
    private TextField modifyPartMinTxt;

    @FXML
    private TextField modifyPartNameTxt;

    @FXML
    private TextField modifyPartPriceTxt;

    @FXML
    private ToggleGroup modifyTogGroup;

    /**
     * In house button.
     * Radion button that assigns text field to machineID.
     * @param event ActionEvent
     */
    @FXML
    void modifyInhouseRadio(ActionEvent event) {
        modifyMachineCompanyLbl.setText("Machine ID");
    }

    /**
     * Oursourced Radio Button.
     * Radio button that assigns text field to Company Name.
     * @param event ActionEvent
     */
    @FXML
    void modifyOutsourcedRadio(ActionEvent event) {
        modifyMachineCompanyLbl.setText("Company Name");
    }

    /**
     * Modify Part Cancel Button.
     * Allows user to cancel the modification of part and go back to main screen.
     * @param event ActionEvent
     */
    @FXML
    void onActionCancelModifyPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Save Modified Part Button.
     * Allows user to save their modified part and update the selected part within the allparts table. Goes through logistical checks.
     * @param event ActionEvent
     */
    @FXML
    void onActionSaveModifyPart(ActionEvent event) throws IOException {

        try {
            int id = Integer.parseInt(modifyPartIdTxt.getText());
            String name = modifyPartNameTxt.getText();
            int stock = Integer.parseInt(modifyPartInvTxt.getText());
            double price = Double.parseDouble(modifyPartPriceTxt.getText());
            int max = Integer.parseInt(modifyPartMaxTxt.getText());
            int min = Integer.parseInt(modifyPartMinTxt.getText());

            // Had to do this because system wasn't recognizing the variable since it was initilized in for loop
            int machineId = 0;
            String companyName = null;
            if (modifyInhouseBtn.isSelected()) {
                machineId = Integer.parseInt(modifyPartMacIDCompNameTxt.getText());
            } else {
                companyName = modifyPartMacIDCompNameTxt.getText();
            }

            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Min Must Be Less Than Max!");
                alert.showAndWait();
            } else if (stock < min || stock > max ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Inventory Must Be Between Min and Max!");
                alert.showAndWait();
            } else {
                if (modifyInhouseBtn.isSelected()) {
                    Inventory.updatePart(currentIndex, new InHouse(id, stock, min, max, name, price, machineId));
                } else {
                    Inventory.updatePart(currentIndex, new Outsourced(id, stock, min, max, name, price, companyName));
                }
                stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Correct Field Values, Please!");
            alert.show();
        }

    }

    //Adding function to send data to modify information

    /**
     * SendOutsourced part method.
     * Creates a method that allows the user to pass the selected outsourced part to the modify screen.
     * @param index Integer of the index of the selected part
     * @param part Part obejct of the selected part to be modified/sent
     */
    public void sendOutsourcedPart (int index, Part part) {

        currentIndex = index;

        modifyPartIdTxt.setText(String.valueOf(part.getId()));
        modifyPartNameTxt.setText(part.getName());
        modifyPartInvTxt.setText(String.valueOf(part.getStock()));
        modifyPartPriceTxt.setText(String.valueOf(part.getPrice()));
        modifyPartMaxTxt.setText(String.valueOf(part.getMax()));
        modifyPartMinTxt.setText(String.valueOf(part.getMin()));
        modifyPartMacIDCompNameTxt.setText(String.valueOf(((Outsourced)part).getCompanyName()));

        // This was tricky as i didn't know where or how to set the button but after talking to a rubber duck, it came
        //to me to set the btn based on this method. Because it was located in the ModifyPartsController, it allowed
        //me to access the button which it wouldn't do in the MainScreen!
        modifyOutsourceBtn.setSelected(true);
        modifyMachineCompanyLbl.setText("Company Name");
    }

    /**
     * Send InHouse Part Method.
     * Creates a method that allows the user to pass the selected in house part to the moidfy screen.
     * @param index Integer of the index of the selected part
     * @param part Part object of the selected part to be modified/sent
     */
    public void sendInhousePart (int index, Part part) {

        currentIndex = index;

        modifyPartIdTxt.setText(String.valueOf(part.getId()));
        modifyPartNameTxt.setText(part.getName());
        modifyPartInvTxt.setText(String.valueOf(part.getStock()));
        modifyPartPriceTxt.setText(String.valueOf(part.getPrice()));
        modifyPartMaxTxt.setText(String.valueOf(part.getMax()));
        modifyPartMinTxt.setText(String.valueOf(part.getMin()));
        modifyPartMacIDCompNameTxt.setText(String.valueOf(((InHouse)part).getMachineId()));

        // This was tricky as i didn't know where or how to set the button but after talking to a rubber duck, it came
        //to me to set the btn based on this method. Because it was located in the ModifyPartsController, it allowed
        //me to access the button which it wouldn't do in the MainScreen!
        modifyInhouseBtn.setSelected(true);
        modifyMachineCompanyLbl.setText("Machine ID");
    }


    /**
     * Main initilize screen for Modify Parts Controller
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}