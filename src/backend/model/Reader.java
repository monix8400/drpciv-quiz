package backend.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    /**
     * function to send quiz results from the quiz to the Finish.xml file
     * @return quiz result from the data.json file
     */
    public static Result getResult() {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/backend/files/data.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray data = (JSONArray) obj;

            // looping over data.json, helpful if we decide that we want to keep track of all results in the future
            for (Object object: data) {
                JSONObject aux = (JSONObject) object;

                // we have a result in r
                JSONObject r = (JSONObject) aux.get("result");

                return new Result(Integer.parseInt(r.get("correctAnswers").toString()),Integer.parseInt(r.get("remainingQuestions").toString()));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * writing in data.json the result of the last quiz taken by the user
     * @param correctAnswers number of actual correct answers to questions
     * @param remainingQuestions number of questions uncompleted by the user
     */
    public static void writeData(int correctAnswers, int remainingQuestions) {
        // New object
        JSONObject objectDetails = new JSONObject();
        objectDetails.put("correctAnswers", correctAnswers);
        objectDetails.put("remainingQuestions", remainingQuestions);

        JSONObject resultObject = new JSONObject();
        resultObject.put("result", objectDetails);

        JSONArray quizResults = new JSONArray();
        quizResults.add(resultObject);

        // writing object to JSON file, format (correctAnswers, remainingQuestions)
        try (FileWriter file = new FileWriter("src/backend/files/data.json")) {
            file.write(quizResults.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reading questions stored in questions.json file
     * @return a list of all questions available in questions.json file
     */
    public List<Question> getQuestions() {

        List<Question> questions = new ArrayList<>();

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/backend/files/questions.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray data = (JSONArray) obj;

            // looping over the questions from the questions.json file
            for (Object object: data) {
                JSONObject aux = (JSONObject) object;

                // here we have a question with 3 different attributes
                JSONObject q = (JSONObject) aux.get("questionObject");

                // initializing an object
                Question qNew = new Question(
                        (String) q.get("question"),
                        (List<String>) q.get("questionAnswers"),
                        (List<Integer>) q.get("correctAnswers")
                );

                // adding question to list
                questions.add(qNew);
            }

            // returning a list with all available questions from the .json file
            return questions;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
