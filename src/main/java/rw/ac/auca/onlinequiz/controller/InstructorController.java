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
import rw.ac.auca.onlinequiz.domain.Instructor;
import rw.ac.auca.onlinequiz.service.InstructorService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/save")
    public ResponseEntity<?> saveInstructor(@RequestBody Instructor instructor) {
        String message = instructorService.saveInstructor(instructor);
        if (message.equalsIgnoreCase("Instructor saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstructorById(@PathVariable UUID id) {
        Optional<Instructor> instructor = instructorService.findById(id);
        if (instructor.isEmpty()) {
            return new ResponseEntity<>("The instructor with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(instructor.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable UUID id, @RequestBody Instructor instructor) {
        String message = instructorService.updateInstructor(id, instructor);
        if (message.equalsIgnoreCase("Instructor updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable UUID id) {
        String message = instructorService.deleteInstructor(id);
        if (message.equalsIgnoreCase("Instructor deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-subject")
    public ResponseEntity<List<Instructor>> getInstructorsBySubject(@RequestParam String name) {
        return new ResponseEntity<>(instructorService.findBySubjectName(name), HttpStatus.OK);
    }

    @PutMapping("/{instructorId}/subjects/{subjectId}")
    public ResponseEntity<?> addSubjectToInstructor(
            @PathVariable UUID instructorId,
            @PathVariable UUID subjectId) {
        String message = instructorService.addSubjectToInstructor(instructorId, subjectId);
        if (message.equalsIgnoreCase("Subject added to the instructor")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equalsIgnoreCase("The instructor already has that subject")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{instructorId}/lab")
    public ResponseEntity<?> moveInstructorToLab(
            @PathVariable UUID instructorId,
            @RequestParam int labNumber) {
        String message = instructorService.changeLab(instructorId, labNumber);
        if (message.equalsIgnoreCase("Lab changed successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else if (message.equalsIgnoreCase("That lab is taken by another instructor")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
