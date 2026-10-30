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
import rw.ac.auca.onlinequiz.domain.Lab;
import rw.ac.auca.onlinequiz.service.LabService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/labs")
public class LabController {

    @Autowired
    private LabService labService;

    @PostMapping("/save")
    public ResponseEntity<?> saveLab(@RequestBody Lab lab) {
        String message = labService.saveLab(lab);
        if (message.equalsIgnoreCase("Lab saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lab>> getAllLabs() {
        return new ResponseEntity<>(labService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLabById(@PathVariable UUID id) {
        Optional<Lab> lab = labService.findById(id);
        if (lab.isEmpty()) {
            return new ResponseEntity<>("The lab with that id does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lab.get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLab(@PathVariable UUID id, @RequestBody Lab lab) {
        String message = labService.updateLab(id, lab);
        if (message.equalsIgnoreCase("Lab updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLab(@PathVariable UUID id) {
        String message = labService.deleteLab(id);
        if (message.equalsIgnoreCase("Lab deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/free")
    public ResponseEntity<List<Lab>> getFreeLabs() {
        return new ResponseEntity<>(labService.findFreeLabs(), HttpStatus.OK);
    }
}
