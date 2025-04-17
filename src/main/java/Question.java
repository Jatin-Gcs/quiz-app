import java.util.List;

public class Question {
    private String text;
    private List<String> options;
    private String correctAnswer;
    private String type;

    // Constructor
    public Question(String text, List<String> options, String correctAnswer, String type) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.type = type;
    }

    // Default constructor for Gson
    public Question() {}

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> options() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}