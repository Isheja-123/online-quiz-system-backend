package rw.ac.auca.onlinequiz.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "feedback_note")
public class FeedbackNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String questionTopic;
    private String comment;
    private int marksAwarded;
    private LocalDate givenDate;

    @ManyToOne
    @JoinColumn(name = "quiz_attempt_id")
    private QuizAttempt quizAttempt;

    public FeedbackNote() {
    }

    public FeedbackNote(UUID id, String questionTopic, String comment, int marksAwarded, LocalDate givenDate, QuizAttempt quizAttempt) {
        this.id = id;
        this.questionTopic = questionTopic;
        this.comment = comment;
        this.marksAwarded = marksAwarded;
        this.givenDate = givenDate;
        this.quizAttempt = quizAttempt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuestionTopic() {
        return questionTopic;
    }

    public void setQuestionTopic(String questionTopic) {
        this.questionTopic = questionTopic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getMarksAwarded() {
        return marksAwarded;
    }

    public void setMarksAwarded(int marksAwarded) {
        this.marksAwarded = marksAwarded;
    }

    public LocalDate getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(LocalDate givenDate) {
        this.givenDate = givenDate;
    }

    public QuizAttempt getQuizAttempt() {
        return quizAttempt;
    }

    public void setQuizAttempt(QuizAttempt quizAttempt) {
        this.quizAttempt = quizAttempt;
    }
}
