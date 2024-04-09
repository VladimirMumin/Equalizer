package GUI;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EqualizerApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/GUI/FXMLDocument.fxml")));

        Scene scene = new Scene(root);
        stage.setTitle("EQUALIZER");
        stage.setScene(scene);
        scene.getStylesheets().add
                (EqualizerApp.class.getResource("/GUI/Style.css").toExternalForm());
        stage.setWidth(800); // Устанавливаем ширину окна в 800 пикселей
        stage.setHeight(500); // Устанавливаем высоту окна в 600 пикселей
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}