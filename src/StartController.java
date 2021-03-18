import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    

    /** initializing window
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public StartController(){

    }

    public void next() throws IOException {
        Main.changeScene("frontend/Module.fxml");
    }
}
