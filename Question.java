import java.util.List;

abstract class Question {
    private final String question;
    private final List<String> options;
    private final int correctOption;
    private final String topic;

    public Question(String question, List<String> options, int correctOption, String topic) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        this.topic = topic;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
    public String getTopic() {
        return topic;
    }
    public abstract int calculateScore(int userAnswer);
}
