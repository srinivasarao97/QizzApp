import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp extends Frame implements ActionListener {
    private Label questionLabel;
    private CheckboxGroup optionsGroup;
    private Checkbox[] options;
    private Button submitButton;
    private int currentQuestionIndex = 0;
    private int score = 0;

    // Define your quiz questions and answers here
    private String[] questions = {
        "What is the capital of France?",
        "What is the largest planet in our solar system?",
        "Which gas do plants absorb from the atmosphere during photosynthesis?"
    };
    private String[] answers = {
        "Paris",
        "Jupiter",
        "Carbon dioxide"
    };

    public QuizApp() {
        setTitle("Quiz App");
        setSize(400, 200);
        setLayout(new FlowLayout());

        questionLabel = new Label(questions[0]);
        add(questionLabel);

        optionsGroup = new CheckboxGroup();
        options = new Checkbox[4];
        options[0] = new Checkbox("Option 1", optionsGroup, false);
        options[1] = new Checkbox("Option 2", optionsGroup, false);
        options[2] = new Checkbox("Option 3", optionsGroup, false);
        options[3] = new Checkbox("Option 4", optionsGroup, false);

        for (Checkbox option : options) {
            add(option);
        }

        submitButton = new Button("Submit");
        add(submitButton);
        submitButton.addActionListener(this);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Check the selected option
            String selectedAnswer = optionsGroup.getSelectedCheckbox().getLabel();
            String correctAnswer = answers[currentQuestionIndex];

            if (selectedAnswer.equals(correctAnswer)) {
                score++;
            }

            currentQuestionIndex++;

            if (currentQuestionIndex < questions.length) {
                // Display the next question
                questionLabel.setText(questions[currentQuestionIndex]);
                for (Checkbox option : options) {
                    option.setState(false);
                }
            } else {
                // Display the final score
                questionLabel.setText("Quiz Complete. Your Score: " + score + " / " + questions.length);
                for (Checkbox option : options) {
                    option.setEnabled(false);
                }
                submitButton.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        QuizApp quiz = new QuizApp();
        quiz.setVisible(true);
    }
}
