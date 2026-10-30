package rw.ac.auca.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.onlinequiz.domain.Lab;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LabRepository extends JpaRepository<Lab, UUID> {

    @Query("SELECT l FROM Lab l WHERE NOT EXISTS (SELECT i FROM Instructor i WHERE i.lab = l) ORDER BY l.labNumber ASC")
    List<Lab> findFreeLabs();

    Optional<Lab> findByLabNumber(int labNumber);
}
