import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuizApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create start screen
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        
        Button startQuizButton = new Button("Start Quiz");
        Button adminModeButton = new Button("Admin Mode");
        
        // Style buttons
        startQuizButton.setStyle("-fx-font-size: 16px;");
        adminModeButton.setStyle("-fx-font-size: 16px;");
        
        root.getChildren().addAll(startQuizButton, adminModeButton);
        
        // Set up scene
        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Interactive Quiz App");
        primaryStage.show();
        
        // Button actions
        QuizController quizController = new QuizController(primaryStage);
        AdminController adminController = new AdminController(primaryStage);
        
        startQuizButton.setOnAction(e -> quizController.showQuizScreen());
        adminModeButton.setOnAction(e -> adminController.showAdminScreen());
    }

    public static void main(String[] args) {
        launch(args);
    }
}