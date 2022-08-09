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

public class HelloApplication extends Application {

    // Creating the counter for unique INTs
    static int partIdCounter = 4;
    static int productIdCounter = 1;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

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


        launch();
    }
}