package dir.controller;

import java.io.IOException;

import dir.App;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller class for the main menu. Handles UI interactions and transitions.
 */
public class MainMenuController {

    @FXML
    private Button startButton;

    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class. Adds a fade-in animation for the image and hover effects for the button.
     */
    @FXML
    public void initialize() {
        FadeTransition ft = new FadeTransition(Duration.millis(1500), imageView);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        startButton.setOnMouseEntered(e -> startButton.setStyle("-fx-background-color: #2980b9;"));
        startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: #3498db;"));
    }

    /**
     * Handles the action event for the start button. Loads the main panel FXML and sets the new scene.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    @FXML
    public void handleStartButtonAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/dir/mainPanel.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 1280, 720);

        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setScene(scene);
    }
}