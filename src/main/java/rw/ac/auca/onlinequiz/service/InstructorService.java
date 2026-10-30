package rw.ac.auca.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.onlinequiz.domain.Instructor;
import rw.ac.auca.onlinequiz.repository.InstructorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public String saveInstructor(Instructor instructor) {
        instructorRepository.save(instructor);
        return "Instructor saved successfully";
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> findById(UUID id) {
        return instructorRepository.findById(id);
    }

    public String updateInstructor(UUID id, Instructor instructor) {
        Optional<Instructor> existingOptional = instructorRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The instructor with that id does not exist";
        }
        Instructor existing = existingOptional.get();
        existing.setFirstName(instructor.getFirstName());
        existing.setLastName(instructor.getLastName());
        existing.setEmail(instructor.getEmail());
        if (instructor.getLab() != null) {
            existing.setLab(instructor.getLab());
        }
        if (instructor.getSubjects() != null) {
            existing.setSubjects(instructor.getSubjects());
        }
        instructorRepository.save(existing);
        return "Instructor updated successfully";
    }

    public String deleteInstructor(UUID id) {
        Optional<Instructor> existingOptional = instructorRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The instructor with that id does not exist";
        }
        instructorRepository.deleteById(id);
        return "Instructor deleted successfully";
    }

    public List<Instructor> findBySubjectName(String name) {
        return instructorRepository.findBySubjectName(name);
    }
}
