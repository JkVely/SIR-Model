package dir;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * App class that extends the JavaFX Application class to start the SIR Model Simulation application.
 * This class is responsible for loading the main menu FXML, applying the CSS styles, and configuring the main stage.
 * 
 * <p>Project repository: <a href="https://github.com/JkVely/SIR-Model">https://github.com/JkVely/SIR-Model</a></p>
 * 
 * <p>Author: Juan Carlos Quintero</p>
 * <p>GitHub: <a href="https://github.com/JkVely">JkVely</a></p>
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/dir/mainMenu.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 1280, 720);

        URL cssUrl = getClass().getResource("/dir/styles/mainMenu.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.out.println("No se pudo cargar el archivo CSS.");
        }

        stage.setTitle("SIR Model Simulation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
