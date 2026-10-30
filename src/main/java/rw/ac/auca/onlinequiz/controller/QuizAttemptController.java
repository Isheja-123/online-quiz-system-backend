package rw.ac.auca.onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.auca.onlinequiz.domain.AttemptStatus;
import rw.ac.auca.onlinequiz.domain.QuizAttempt;
import rw.ac.auca.onlinequiz.service.QuizAttemptService;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/quiz-attempts")
public class QuizAttemptController {

    @Autowired
    private QuizAttemptService quizAttemptService;

    @PostMapping("/save")
    public ResponseEntity<?> saveQuizAttempt(@RequestBody QuizAttempt quizAttempt) {
        String message = quizAttemptService.saveQuizAttempt(quizAttempt);
        if (message.equalsIgnoreCase("Quiz attempt saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equalsIgnoreCase("Quiz attempt date cannot be in the past")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if (message.equalsIgnoreCase("Instructor already has a quiz attempt on that date")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizAttempt>> getAllQuizAttempts() {
        return new ResponseEntity<>(quizAttemptService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizAttemptById(@PathVariable UUID id) {
        Optional<QuizAttempt> quizAttempt = quizAttemptService.findById(id);
        if (quizAttempt.isEmpty()) {
            return new ResponseEntity<>("The quiz attempt with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(quizAttempt.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuizAttempt(@PathVariable UUID id, @RequestBody QuizAttempt quizAttempt) {
        String message = quizAttemptService.updateQuizAttempt(id, quizAttempt);
        if (message.equalsIgnoreCase("Quiz attempt updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuizAttempt(@PathVariable UUID id) {
        String message = quizAttemptService.deleteQuizAttempt(id);
        if (message.equalsIgnoreCase("Quiz attempt deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<QuizAttempt>> getQuizAttemptsByStatus(@RequestParam AttemptStatus status) {
        return new ResponseEntity<>(quizAttemptService.findByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<QuizAttempt>> getQuizAttemptsBetweenDates(
            @RequestParam String start,
            @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(quizAttemptService.findBetweenDates(startDate, endDate), HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateQuizAttemptStatus(@PathVariable UUID id, @RequestParam AttemptStatus status) {
        String message = quizAttemptService.updateStatus(id, status);
        if (message.equalsIgnoreCase("Quiz attempt status updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equalsIgnoreCase("The quiz attempt with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
