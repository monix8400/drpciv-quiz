import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("frontend/Start.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("DRPCIV Wannabe");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * function to switch between scenes
     */
    public static void changeScene(String filename) throws IOException {
        Parent pane = FXMLLoader.load(Main.class.getResource(filename));
        Main.primaryStage.getScene().setRoot(pane);
    }
}