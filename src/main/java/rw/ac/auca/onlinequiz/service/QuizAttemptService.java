package rw.ac.auca.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.ac.auca.onlinequiz.domain.AttemptStatus;
import rw.ac.auca.onlinequiz.domain.QuizAttempt;
import rw.ac.auca.onlinequiz.repository.FeedbackNoteRepository;
import rw.ac.auca.onlinequiz.repository.QuizAttemptRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizAttemptService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private FeedbackNoteRepository feedbackNoteRepository;

    public String saveQuizAttempt(QuizAttempt quizAttempt) {
        if (quizAttempt.getAttemptDate() != null && quizAttempt.getAttemptDate().isBefore(LocalDate.now())) {
            return "Quiz attempt date cannot be in the past";
        }
        if (quizAttempt.getInstructor() != null && quizAttempt.getAttemptDate() != null) {
            boolean conflict = quizAttemptRepository.existsByInstructorAndAttemptDateAndStatusNot(
                    quizAttempt.getInstructor(),
                    quizAttempt.getAttemptDate(),
                    AttemptStatus.ABANDONED
            );
            if (conflict) {
                return "Instructor already has a quiz attempt on that date";
            }
        }
        quizAttempt.setStatus(AttemptStatus.STARTED);
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

    public String updateStatus(UUID id, AttemptStatus newStatus) {
        Optional<QuizAttempt> existingOptional = quizAttemptRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The quiz attempt with that id does not exist";
        }
        QuizAttempt existing = existingOptional.get();
        AttemptStatus current = existing.getStatus();

        boolean allowed = false;
        if (current == AttemptStatus.STARTED) {
            if (newStatus == AttemptStatus.SUBMITTED || newStatus == AttemptStatus.ABANDONED) {
                allowed = true;
            }
        } else if (current == AttemptStatus.SUBMITTED) {
            if (newStatus == AttemptStatus.GRADED || newStatus == AttemptStatus.ABANDONED) {
                allowed = true;
            }
        }

        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }

        existing.setStatus(newStatus);
        quizAttemptRepository.save(existing);
        return "Quiz attempt status updated successfully";
    }

    public String deleteQuizAttempt(UUID id) {
        Optional<QuizAttempt> existingOptional = quizAttemptRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The quiz attempt with that id does not exist";
        }
        QuizAttempt existing = existingOptional.get();
        if (feedbackNoteRepository.existsByQuizAttempt(existing)) {
            return "That quiz attempt has feedback notes, delete them first";
        }
        quizAttemptRepository.deleteById(id);
        return "Quiz attempt deleted successfully";
    }

    public List<QuizAttempt> findByStatus(AttemptStatus status) {
        return quizAttemptRepository.findByStatusOrderByAttemptDateAsc(status);
    }

    public List<QuizAttempt> findBetweenDates(LocalDate start, LocalDate end) {
        return quizAttemptRepository.findByAttemptDateBetweenOrderByAttemptDateAsc(start, end);
    }

    public List<Object[]> getStatsByStatus() {
        return quizAttemptRepository.countQuizAttemptsByStatus();
    }

    @Transactional
    public String cancelDay(UUID instructorId, LocalDate date) {
        int cancelledCount = quizAttemptRepository.cancelDayForInstructor(instructorId, date);
        return cancelledCount + " quiz attempts cancelled";
    }

    public Page<QuizAttempt> findPage(Pageable pageable) {
        return quizAttemptRepository.findAll(pageable);
    }
}
