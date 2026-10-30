package rw.ac.auca.onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.auca.onlinequiz.domain.FeedbackNote;
import rw.ac.auca.onlinequiz.service.FeedbackNoteService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/feedback-notes")
public class FeedbackNoteController {

    @Autowired
    private FeedbackNoteService feedbackNoteService;

    @PostMapping("/save")
    public ResponseEntity<?> saveFeedbackNote(
            @RequestParam UUID quizAttemptId,
            @RequestBody FeedbackNote feedbackNote) {
        String message = feedbackNoteService.saveFeedbackNote(quizAttemptId, feedbackNote);
        if (message.equalsIgnoreCase("Feedback note saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equalsIgnoreCase("The quiz attempt with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if (message.equalsIgnoreCase("Feedback notes are only for GRADED quiz attempts")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackNote>> getAllFeedbackNotes() {
        return new ResponseEntity<>(feedbackNoteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedbackNoteById(@PathVariable UUID id) {
        Optional<FeedbackNote> feedbackNoteOptional = feedbackNoteService.findById(id);
        if (feedbackNoteOptional.isEmpty()) {
            return new ResponseEntity<>("The feedback note with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feedbackNoteOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/student/{regNumber}")
    public ResponseEntity<List<FeedbackNote>> getFeedbackNotesByStudent(@PathVariable String regNumber) {
        return new ResponseEntity<>(feedbackNoteService.findByStudentRegNumber(regNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFeedbackNote(@PathVariable UUID id) {
        String message = feedbackNoteService.deleteFeedbackNote(id);
        if (message.equalsIgnoreCase("Feedback note deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
