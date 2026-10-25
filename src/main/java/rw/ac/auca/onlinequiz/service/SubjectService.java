package rw.ac.auca.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.onlinequiz.domain.Subject;
import rw.ac.auca.onlinequiz.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public String saveSubject(Subject subject) {
        subjectRepository.save(subject);
        return "Subject saved successfully";
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> findById(UUID id) {
        return subjectRepository.findById(id);
    }

    public String updateSubject(UUID id, Subject subject) {
        Optional<Subject> existingOptional = subjectRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The subject with that id does not exist";
        }
        Subject existing = existingOptional.get();
        existing.setName(subject.getName());
        subjectRepository.save(existing);
        return "Subject updated successfully";
    }

    public String deleteSubject(UUID id) {
        Optional<Subject> existingOptional = subjectRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The subject with that id does not exist";
        }
        subjectRepository.deleteById(id);
        return "Subject deleted successfully";
    }
}
