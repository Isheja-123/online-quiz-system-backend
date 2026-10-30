package rw.ac.auca.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.onlinequiz.domain.Instructor;
import rw.ac.auca.onlinequiz.domain.Lab;
import rw.ac.auca.onlinequiz.domain.Subject;
import rw.ac.auca.onlinequiz.repository.InstructorRepository;
import rw.ac.auca.onlinequiz.repository.LabRepository;
import rw.ac.auca.onlinequiz.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LabRepository labRepository;

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

    public String addSubjectToInstructor(UUID instructorId, UUID subjectId) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(instructorId);
        if (instructorOptional.isEmpty()) {
            return "The instructor with that id does not exist";
        }
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        if (subjectOptional.isEmpty()) {
            return "That subject does not exist";
        }

        Instructor instructor = instructorOptional.get();
        Subject subject = subjectOptional.get();

        for (Subject existingSubject : instructor.getSubjects()) {
            if (existingSubject.getId().equals(subjectId)) {
                return "The instructor already has that subject";
            }
        }

        instructor.getSubjects().add(subject);
        instructorRepository.save(instructor);
        return "Subject added to the instructor";
    }

    public String changeLab(UUID instructorId, int labNumber) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(instructorId);
        if (instructorOptional.isEmpty()) {
            return "The instructor with that id does not exist";
        }
        Optional<Lab> labOptional = labRepository.findByLabNumber(labNumber);
        if (labOptional.isEmpty()) {
            return "The lab with that labNumber does not exist";
        }

        Lab lab = labOptional.get();
        if (instructorRepository.existsByLab(lab)) {
            return "That lab is taken by another instructor";
        }

        Instructor instructor = instructorOptional.get();
        instructor.setLab(lab);
        instructorRepository.save(instructor);
        return "Lab changed successfully";
    }
}
