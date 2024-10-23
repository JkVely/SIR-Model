package dir;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo FXML del menú principal
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/dir/mainMenu.fxml"));
        Parent root = fxmlLoader.load();

        // Crear la escena con el contenido del archivo FXML
        Scene scene = new Scene(root, 1280, 720);

        // Cargar la hoja de estilos CSS
        URL cssUrl = getClass().getResource("/dir/styles/mainMenu.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.out.println("No se pudo cargar el archivo CSS.");
        }

        // Configurar y mostrar la ventana principal
        stage.setTitle("SIR Model Simulation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
