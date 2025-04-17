import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class AdminController {
    private Stage stage;

    public AdminController(Stage stage) {
        this.stage = stage;
    }

    public void showAdminScreen() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Form fields
        Label questionLabel = new Label("Question:");
        TextField questionField = new TextField();
        Label optionsLabel = new Label("Options (comma-separated):");
        TextField optionsField = new TextField();
        Label answerLabel = new Label("Correct Answer:");
        TextField answerField = new TextField();

        Button saveButton = new Button("Save Question");
        Button backButton = new Button("Back");

        // Add to grid
        grid.add(questionLabel, 0, 0);
        grid.add(questionField, 1, 0);
        grid.add(optionsLabel, 0, 1);
        grid.add(optionsField, 1, 1);
        grid.add(answerLabel, 0, 2);
        grid.add(answerField, 1, 2);
        grid.add(saveButton, 0, 3);
        grid.add(backButton, 1, 3);

        // Save action
        saveButton.setOnAction(e -> {
            String question = questionField.getText();
            String options = optionsField.getText();
            String answer = answerField.getText();

            if (question.isEmpty() || options.isEmpty() || answer.isEmpty()) {
                showAlert("All fields are required!");
                return;
            }

            Question q = new Question(question, Arrays.asList(options.split(",")), answer, "multiple-choice");
            FileHandler.saveQuestion(q);
            showAlert("Question saved!");
            questionField.clear();
            optionsField.clear();
            answerField.clear();
        });

        // Back action
        backButton.setOnAction(e -> {
            QuizApp app = new QuizApp();
            app.start(stage);
        });

        Scene scene = new Scene(grid, 500, 300);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(message);
        alert.showAndWait();
    }
}