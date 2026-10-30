package rw.ac.auca.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.onlinequiz.domain.AttemptStatus;
import rw.ac.auca.onlinequiz.domain.FeedbackNote;
import rw.ac.auca.onlinequiz.domain.QuizAttempt;
import rw.ac.auca.onlinequiz.repository.FeedbackNoteRepository;
import rw.ac.auca.onlinequiz.repository.QuizAttemptRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackNoteService {

    @Autowired
    private FeedbackNoteRepository feedbackNoteRepository;

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    public String saveFeedbackNote(UUID quizAttemptId, FeedbackNote feedbackNote) {
        Optional<QuizAttempt> quizAttemptOptional = quizAttemptRepository.findById(quizAttemptId);
        if (quizAttemptOptional.isEmpty()) {
            return "The quiz attempt with that id does not exist";
        }

        QuizAttempt quizAttempt = quizAttemptOptional.get();
        if (quizAttempt.getStatus() != AttemptStatus.GRADED) {
            return "Feedback notes are only for GRADED quiz attempts";
        }

        feedbackNote.setQuizAttempt(quizAttempt);
        feedbackNote.setGivenDate(LocalDate.now());
        feedbackNoteRepository.save(feedbackNote);
        return "Feedback note saved successfully";
    }

    public List<FeedbackNote> findAll() {
        return feedbackNoteRepository.findAll();
    }

    public Optional<FeedbackNote> findById(UUID id) {
        return feedbackNoteRepository.findById(id);
    }

    public List<FeedbackNote> findByStudentRegNumber(String regNumber) {
        return feedbackNoteRepository.findByStudentRegNumber(regNumber);
    }

    public String deleteFeedbackNote(UUID id) {
        Optional<FeedbackNote> existingOptional = feedbackNoteRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The feedback note with that id does not exist";
        }
        feedbackNoteRepository.deleteById(id);
        return "Feedback note deleted successfully";
    }
}
