package rw.ac.auca.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.onlinequiz.domain.Instructor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, UUID> {

    @Query("SELECT DISTINCT i FROM Instructor i JOIN i.subjects s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Instructor> findBySubjectName(@Param("name") String name);
}
