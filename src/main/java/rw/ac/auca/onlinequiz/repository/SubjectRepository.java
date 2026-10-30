package rw.ac.auca.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.onlinequiz.domain.Subject;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {

    @Query("SELECT s FROM Subject s WHERE s.instructors IS EMPTY")
    List<Subject> findUnusedSubjects();
}
