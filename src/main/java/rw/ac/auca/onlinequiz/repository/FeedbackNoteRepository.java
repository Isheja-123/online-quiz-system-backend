package rw.ac.auca.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ac.auca.onlinequiz.domain.FeedbackNote;
import rw.ac.auca.onlinequiz.domain.QuizAttempt;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackNoteRepository extends JpaRepository<FeedbackNote, UUID> {

    @Query("SELECT f FROM FeedbackNote f WHERE f.quizAttempt.student.regNumber = :regNumber ORDER BY f.givenDate DESC")
    List<FeedbackNote> findByStudentRegNumber(@Param("regNumber") String regNumber);

    boolean existsByQuizAttempt(QuizAttempt quizAttempt);
}
