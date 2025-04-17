import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class QuizController {
    private Stage stage;
    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private Label timerLabel;
    private Timeline timer;
    private ToggleGroup toggleGroup;

    public QuizController(Stage stage) {
        this.stage = stage;
        this.questions = FileHandler.loadQuestions();
    }

    public void showQuizScreen() {
        if (questions.isEmpty()) {
            showAlert("No questions available!");
            return;
        }

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Question label
        Label questionLabel = new Label(questions.get(currentIndex).getText());
        questionLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Options
        toggleGroup = new ToggleGroup();
        VBox optionsBox = new VBox(10);
        for (String option : questions.get(currentIndex).options()) {
            RadioButton rb = new RadioButton(option);
            rb.setToggleGroup(toggleGroup);
            optionsBox.getChildren().add(rb);
        }

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-size: 14px;");

        // Timer
        timerLabel = new Label("Time: 30");
        timerLabel.setStyle("-fx-font-size: 14px;");
        setupTimer(submitButton);

        root.getChildren().addAll(questionLabel, optionsBox, submitButton, timerLabel);

        // Submit action
        submitButton.setOnAction(e -> handleSubmission());

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
    }

    private void setupTimer(Button submitButton) {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            int timeLeft = Integer.parseInt(timerLabel.getText().split(": ")[1]) - 1;
            timerLabel.setText("Time: " + timeLeft);
            if (timeLeft <= 0) {
                timer.stop();
                handleSubmission();
            }
        }));
        timer.setCycleCount(30);
        timer.play();
    }

    private void handleSubmission() {
        timer.stop();
        RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();
        String userAnswer = selected != null ? selected.getText() : "";
        if (userAnswer.equals(questions.get(currentIndex).getCorrectAnswer())) {
            score++;
        }

        currentIndex++;
        if (currentIndex < questions.size()) {
            showQuizScreen();
        } else {
            ResultController resultController = new ResultController(stage, score, questions.size());
            resultController.showResultScreen();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}