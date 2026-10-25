package rw.ac.auca.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.onlinequiz.domain.QuizAttempt;
import rw.ac.auca.onlinequiz.repository.QuizAttemptRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizAttemptService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    public String saveQuizAttempt(QuizAttempt quizAttempt) {
        quizAttemptRepository.save(quizAttempt);
        return "Quiz attempt saved successfully";
    }

    public List<QuizAttempt> findAll() {
        return quizAttemptRepository.findAll();
    }

    public Optional<QuizAttempt> findById(UUID id) {
        return quizAttemptRepository.findById(id);
    }

    public String updateQuizAttempt(UUID id, QuizAttempt quizAttempt) {
        Optional<QuizAttempt> existingOptional = quizAttemptRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The quiz attempt with that id does not exist";
        }
        QuizAttempt existing = existingOptional.get();
        existing.setAttemptDate(quizAttempt.getAttemptDate());
        existing.setStatus(quizAttempt.getStatus());
        existing.setQuizTitle(quizAttempt.getQuizTitle());
        existing.setScore(quizAttempt.getScore());
        if (quizAttempt.getStudent() != null) {
            existing.setStudent(quizAttempt.getStudent());
        }
        if (quizAttempt.getInstructor() != null) {
            existing.setInstructor(quizAttempt.getInstructor());
        }
        quizAttemptRepository.save(existing);
        return "Quiz attempt updated successfully";
    }

    public String deleteQuizAttempt(UUID id) {
        Optional<QuizAttempt> existingOptional = quizAttemptRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The quiz attempt with that id does not exist";
        }
        quizAttemptRepository.deleteById(id);
        return "Quiz attempt deleted successfully";
    }
}
