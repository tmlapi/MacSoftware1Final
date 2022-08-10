package software1.finalsoftwarev2;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main application that starts the application.
 *
 * <p><b>RUNTIME ERROR</b> Found in the AppProductController.java file in the JavaDocs for the addProductSaveBtn</p>
 *<p><b>RUNTIME ERROR</b> Found in the ModifyProductController.java file in the JavaDocs for modifyProductSaveBtn</p>
 * <p><b>FUTURE ENHANCEMENTS</b> The future enhancements may look at incorporating a database that keeps track of changes; even once the program close.
 * It could also aim to improving redundancies to make less code!</p>
 */
public class Main extends Application {

    // Creating the counter for unique INTs
    /**
     * Creates a unique counter field for the ids that get auto generated!
     */
    public static int partIdCounter = 0;

    /**
     * Creates a unique counter field for the New products that get auto generated
     */
    public static int productIdCounter = 0;

    /**
     * Starts the initial stage and scene that the application will be based off of and build on
     * @param stage Creates a new Stage object
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method.
     * This is the main method that loads test data in and actually launches app!
     * @param args Main arguments of the program
     *
     *<p><b>JAVADOCS LOCATION</b> The location of JavaDocs folder, should be in the main category of files labeled
     * 'JavaDocs'</p>
     */
    public static void main(String[] args) {


        // Creating Test Data
        InHouse brake = new InHouse(1, 14, 1, 20, "Brake Disks", 119.99, 3);
        InHouse handleBars = new InHouse(2, 20, 1, 20, "Handle Bars", 169.69, 2);
        Outsourced frame = new Outsourced(3, 7, 1, 10, "Trex Frame", 421.76, "Trex");
        Outsourced tires = new Outsourced(4, 80, 1, 100, "Maxxis Tires", 75.21, "Maxxis");
        Product trexBike = new Product(1, 2, 1, 5, "Trex Bike", 820.13);

        // Adding test data to Observable Lists
        Inventory.addPart(brake);
        Inventory.addPart(handleBars);
        Inventory.addPart(frame);
        Inventory.addPart(tires);
        Inventory.addProduct(trexBike);


        /**
         * Launches the application
         */
        launch();
    }
}