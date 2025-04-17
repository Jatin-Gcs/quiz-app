import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResultController {
    private Stage stage;
    private int score;
    private int total;

    public ResultController(Stage stage, int score, int total) {
        this.stage = stage;
        this.score = score;
        this.total = total;
    }

    public void showResultScreen() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label scoreLabel = new Label("Score: " + score + "/" + total);
        scoreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ProgressBar progressBar = new ProgressBar((double) score / total);
        progressBar.setPrefWidth(200);

        Button restartButton = new Button("Restart Quiz");
        restartButton.setStyle("-fx-font-size: 14px;");

        root.getChildren().addAll(scoreLabel, progressBar, restartButton);

        // Restart action
        restartButton.setOnAction(e -> {
            QuizApp app = new QuizApp();
            app.start(stage);
        });

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
    }
}