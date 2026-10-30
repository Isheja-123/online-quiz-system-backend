package rw.ac.auca.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ac.auca.onlinequiz.domain.AttemptStatus;
import rw.ac.auca.onlinequiz.domain.Instructor;
import rw.ac.auca.onlinequiz.domain.QuizAttempt;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, UUID> {

    List<QuizAttempt> findByStatusOrderByAttemptDateAsc(AttemptStatus status);

    List<QuizAttempt> findByAttemptDateBetweenOrderByAttemptDateAsc(LocalDate start, LocalDate end);

    boolean existsByInstructorAndAttemptDateAndStatusNot(Instructor instructor, LocalDate attemptDate, AttemptStatus status);

    @Query("SELECT q.status, COUNT(q) FROM QuizAttempt q GROUP BY q.status")
    List<Object[]> countQuizAttemptsByStatus();

    @Modifying
    @Query("UPDATE QuizAttempt q SET q.status = rw.ac.auca.onlinequiz.domain.AttemptStatus.ABANDONED WHERE q.instructor.id = :instructorId AND q.attemptDate = :attemptDate AND q.status != rw.ac.auca.onlinequiz.domain.AttemptStatus.GRADED")
    int cancelDayForInstructor(@Param("instructorId") UUID instructorId, @Param("attemptDate") LocalDate attemptDate);

    long countByAttemptDate(LocalDate attemptDate);

    long countByStatus(AttemptStatus status);
}
