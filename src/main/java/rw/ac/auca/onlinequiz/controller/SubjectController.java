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
import rw.ac.auca.onlinequiz.domain.Subject;
import rw.ac.auca.onlinequiz.service.SubjectService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/save")
    public ResponseEntity<?> saveSubject(@RequestBody Subject subject) {
        String message = subjectService.saveSubject(subject);
        if (message.equalsIgnoreCase("Subject saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable UUID id) {
        Optional<Subject> subject = subjectService.findById(id);
        if (subject.isEmpty()) {
            return new ResponseEntity<>("The subject with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subject.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable UUID id, @RequestBody Subject subject) {
        String message = subjectService.updateSubject(id, subject);
        if (message.equalsIgnoreCase("Subject updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable UUID id) {
        String message = subjectService.deleteSubject(id);
        if (message.equalsIgnoreCase("Subject deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/unused")
    public ResponseEntity<List<Subject>> getUnusedSubjects() {
        return new ResponseEntity<>(subjectService.findUnusedSubjects(), HttpStatus.OK);
    }
}
