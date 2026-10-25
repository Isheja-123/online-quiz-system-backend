package rw.ac.auca.onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.auca.onlinequiz.domain.QuizAttempt;
import rw.ac.auca.onlinequiz.service.QuizAttemptService;

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
}
