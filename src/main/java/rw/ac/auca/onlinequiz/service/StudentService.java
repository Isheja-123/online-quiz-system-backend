package rw.ac.auca.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.onlinequiz.domain.Student;
import rw.ac.auca.onlinequiz.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String saveStudent(Student student) {
        studentRepository.save(student);
        return "Student saved successfully";
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(UUID id) {
        return studentRepository.findById(id);
    }

    public String updateStudent(UUID id, Student student) {
        Optional<Student> existingOptional = studentRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The student with that id does not exist";
        }
        Student existing = existingOptional.get();
        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setRegNumber(student.getRegNumber());
        existing.setEmail(student.getEmail());
        studentRepository.save(existing);
        return "Student updated successfully";
    }

    public String deleteStudent(UUID id) {
        Optional<Student> existingOptional = studentRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The student with that id does not exist";
        }
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }

    public List<Student> findByLastName(String lastName) {
        return studentRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}
