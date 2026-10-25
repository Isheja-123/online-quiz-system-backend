package rw.ac.auca.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.onlinequiz.domain.Lab;

import java.util.UUID;

@Repository
public interface LabRepository extends JpaRepository<Lab, UUID> {
}
