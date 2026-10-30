package rw.ac.auca.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.onlinequiz.domain.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT q.student FROM QuizAttempt q WHERE q.instructor.id = :instructorId")
    List<Student> findStudentsOfInstructor(@Param("instructorId") UUID instructorId);
}
