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

public class MainMenuController {

    @FXML
    private Button startButton;

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        // Add a fade-in animation for the image
        FadeTransition ft = new FadeTransition(Duration.millis(1500), imageView);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();

        // Add hover effect for the button
        startButton.setOnMouseEntered(e -> startButton.setStyle("-fx-background-color: #2980b9;"));
        startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: #3498db;"));
    }

    @FXML
    public void handleStartButtonAction() throws IOException {
        // Cargar el archivo FXML de MainPanel
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/dir/mainPanel.fxml"));
        Parent root = fxmlLoader.load();

        // Crear la escena con el contenido del archivo FXML
        Scene scene = new Scene(root, 1280, 720);

        // Configurar y mostrar la nueva escena
        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setScene(scene);
    }
}