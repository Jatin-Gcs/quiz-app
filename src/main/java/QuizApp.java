import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResultController {
    private Stage stage;
    private int score;
    private int total;
    private QuizApp quizApp; // Reference to QuizApp

    public ResultController(Stage stage, int score, int total) {
        this.stage = stage;
        this.score = score;
        this.total = total;
        this.quizApp = new QuizApp(); // For accessing showStartScreen
    }

    public void showResultScreen() {
        // Create root layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Create centered VBox for content
        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setMaxWidth(400);
        contentBox.setStyle("-fx-background-color: #ffffff; -fx-padding: 20; -fx-border-radius: 5; -fx-background-radius: 5;");

        // Score label
        Label scoreLabel = new Label("Score: " + score + "/" + total);
        scoreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Progress bar
        ProgressBar progressBar = new ProgressBar((double) score / total);
        progressBar.setPrefWidth(300); // Fixed width for progress bar

        // Restart button
        Button restartButton = new Button("Restart Quiz");
        restartButton.setStyle("-fx-font-size: 14px;");
        restartButton.setMaxWidth(200);

        contentBox.getChildren().addAll(scoreLabel, progressBar, restartButton);

        // Center content in BorderPane
        root.setCenter(contentBox);

        // Set up scene
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);

        // Restart action
        restartButton.setOnAction(e -> quizApp.showStartScreen());
    }
}