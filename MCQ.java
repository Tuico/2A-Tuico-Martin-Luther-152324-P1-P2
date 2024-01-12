import java.util.List;

class McQ extends Question {

    public McQ(String question, List<String> options, int correctOption, String topic) {
        super(question, options, correctOption, topic);
    }

    @Override
    public int calculateScore(int userAnswer) {
        return (userAnswer == getCorrectOption()) ? 1 : 0;
    }
}

