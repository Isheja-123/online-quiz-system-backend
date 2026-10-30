package rw.ac.auca.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.onlinequiz.domain.Lab;
import rw.ac.auca.onlinequiz.repository.LabRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LabService {

    @Autowired
    private LabRepository labRepository;

    public String saveLab(Lab lab) {
        labRepository.save(lab);
        return "Lab saved successfully";
    }

    public List<Lab> findAll() {
        return labRepository.findAll();
    }

    public Optional<Lab> findById(UUID id) {
        return labRepository.findById(id);
    }

    public String updateLab(UUID id, Lab lab) {
        Optional<Lab> existingOptional = labRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The lab with that id does not exist";
        }
        Lab existing = existingOptional.get();
        existing.setLabNumber(lab.getLabNumber());
        existing.setName(lab.getName());
        labRepository.save(existing);
        return "Lab updated successfully";
    }

    public String deleteLab(UUID id) {
        Optional<Lab> existingOptional = labRepository.findById(id);
        if (existingOptional.isEmpty()) {
            return "The lab with that id does not exist";
        }
        labRepository.deleteById(id);
        return "Lab deleted successfully";
    }

    public List<Lab> findFreeLabs() {
        return labRepository.findFreeLabs();
    }
}
