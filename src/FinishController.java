import backend.model.Reader;
import backend.model.Result;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinishController implements Initializable {

    @FXML
    private Text correctAnswers;
    @FXML
    private Text wrongAnswers;
    @FXML
    private Text finalAnswer;

    /** initializing window
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // reading result data from data.json
        Result r = Reader.getResult();

        assert r != null;
        correctAnswers.setText(String.valueOf(r.getCorrectAnswers()));
        wrongAnswers.setText(String.valueOf(26 - r.getRemainingQuestions() - r.getCorrectAnswers()));

        // checking if user passed test or not
        if(r.getCorrectAnswers()>=22){
            finalAnswer.setText("Admis");
            finalAnswer.setFill(Color.GREEN);
        }
        else{
            finalAnswer.setText("Respins");
            finalAnswer.setFill(Color.RED);
        }
    }

    public FinishController(){

    }

    public void next() throws IOException {
        Main.changeScene("frontend/Module.fxml");
    }
}
