package oopsystems.classProblem;

import java.util.ArrayList;
import java.util.List;


// Student class
class ExamStudent {

    private String name;

    public ExamStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


// Abstract Question class
abstract class Question {

    private int questionNumber;
    private String questionText;
    private int points;

    public Question(
            int questionNumber,
            String questionText,
            int points) {

        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.points = points;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getPoints() {
        return points;
    }

    // Each question type evaluates differently
    public abstract boolean evaluate(String answer);
}


// Multiple Choice Question
class MultipleChoiceQuestion extends Question {

    private String correctAnswer;

    public MultipleChoiceQuestion(
            int questionNumber,
            String questionText,
            int points,
            String correctAnswer) {

        super(questionNumber, questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}


// True / False Question
class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(
            int questionNumber,
            String questionText,
            int points,
            boolean correctAnswer) {

        super(questionNumber, questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {

        boolean studentAnswer =
                Boolean.parseBoolean(answer);

        return studentAnswer == correctAnswer;
    }
}


// Answer class
class ExamAnswer {

    private Question question;
    private String answer;

    public ExamAnswer(
            Question question,
            String answer) {

        this.question = question;
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return question.evaluate(answer);
    }

    public int getPoints() {

        if (isCorrect()) {
            return question.getPoints();
        }

        return 0;
    }
}


// Examination class
class Examination {

    private String examName;
    private List<Question> questions;

    public Examination(String examName) {
        this.examName = examName;
        this.questions = new ArrayList<>();
    }

    public String getExamName() {
        return examName;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getTotalPoints() {

        int total = 0;

        for (Question question : questions) {
            total += question.getPoints();
        }

        return total;
    }
}


// Attempt class
class ExamAttempt {

    private ExamStudent student;
    private Examination examination;
    private List<ExamAnswer> answers;
    private String status;

    public ExamAttempt(
            ExamStudent student,
            Examination examination) {

        this.student = student;
        this.examination = examination;
        this.answers = new ArrayList<>();
        this.status = "InProgress";
    }


    public void start() {

        System.out.println(
                examination.getExamName()
                        + " started by "
                        + student.getName()
                        + "."
        );
    }


    public void answerQuestion(
            Question question,
            String answer) {

        // Answers cannot be changed after submission
        if (status.equals("Submitted")) {

            System.out.println(
                    "Cannot change answers for a submitted examination."
            );

            return;
        }

        // Check whether question belongs to exam
        if (!examination.getQuestions().contains(question)) {

            System.out.println(
                    "Question does not belong to this examination."
            );

            return;
        }

        // Check whether question already has an answer
        for (ExamAnswer existingAnswer : answers) {

            if (existingAnswer.getQuestion() == question) {

                System.out.println(
                        "Question "
                                + question.getQuestionNumber()
                                + " has already been answered."
                );

                return;
            }
        }

        answers.add(
                new ExamAnswer(question, answer)
        );

        System.out.println(
                "Answer recorded for Question "
                        + question.getQuestionNumber()
                        + "."
        );
    }


    public void submit() {

        if (status.equals("Submitted")) {

            System.out.println(
                    "Examination has already been submitted."
            );

            return;
        }

        status = "Submitted";

        System.out.println(
                examination.getExamName()
                        + " submitted by "
                        + student.getName()
                        + "."
        );

        calculateResult();
    }


    private void calculateResult() {

        int totalScore = 0;

        for (ExamAnswer answer : answers) {

            if (answer.isCorrect()) {

                System.out.println(
                        "Result: Question "
                                + answer.getQuestion()
                                .getQuestionNumber()
                                + ": Correct ("
                                + answer.getPoints()
                                + " points)"
                );

            } else {

                System.out.println(
                        "Result: Question "
                                + answer.getQuestion()
                                .getQuestionNumber()
                                + ": Incorrect (0 points)"
                );
            }

            totalScore += answer.getPoints();
        }

        System.out.println(
                "Total score: "
                        + totalScore
                        + "/"
                        + examination.getTotalPoints()
                        + "."
        );
    }
}


// Main class
public class OnlineExaminationSystem {

    public static void main(String[] args) {

        // Create student
        ExamStudent student =
                new ExamStudent("Student 1");


        // Create examination
        Examination exam =
                new Examination("Exam A");


        // Question 1 - MCQ
        Question question1 =
                new MultipleChoiceQuestion(
                        1,
                        "Which option is correct?",
                        5,
                        "C"
                );


        // Question 2 - True/False
        Question question2 =
                new TrueFalseQuestion(
                        2,
                        "Java is an object-oriented language.",
                        5,
                        false
                );


        // Add questions to examination
        exam.addQuestion(question1);
        exam.addQuestion(question2);


        // Create attempt
        ExamAttempt attempt =
                new ExamAttempt(
                        student,
                        exam
                );


        // Start examination
        attempt.start();


        // Student answers Question 1
        attempt.answerQuestion(
                question1,
                "C"
        );


        // Student answers Question 2
        attempt.answerQuestion(
                question2,
                "True"
        );


        // Submit examination
        attempt.submit();


        // Student tries to change Question 1
        attempt.answerQuestion(
                question1,
                "A"
        );
    }
}