package rw.ac.auca.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.onlinequiz.domain.QuizAttempt;

import rw.ac.auca.onlinequiz.domain.AttemptStatus;
import rw.ac.auca.onlinequiz.domain.Instructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, UUID> {

    List<QuizAttempt> findByStatusOrderByAttemptDateAsc(AttemptStatus status);

    List<QuizAttempt> findByAttemptDateBetweenOrderByAttemptDateAsc(LocalDate start, LocalDate end);

    boolean existsByInstructorAndAttemptDateAndStatusNot(Instructor instructor, LocalDate attemptDate, AttemptStatus status);
}
