import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "questions.txt";

    public static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String text = parts[0];
                    List<String> options = List.of(parts[1].split(","));
                    String correctAnswer = parts[2];
                    String type = parts[3];
                    questions.add(new Question(text, options, correctAnswer, type));
                }
            }
        } catch (IOException e) {
            // Return empty list if file doesn't exist
        }
        return questions;
    }

    public static void saveQuestion(Question question) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = question.getText() + "|" +
                          String.join(",", question.options()) + "|" +
                          question.getCorrectAnswer() + "|" +
                          question.getType();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}