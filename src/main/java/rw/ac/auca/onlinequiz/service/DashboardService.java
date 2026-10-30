package rw.ac.auca.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.onlinequiz.domain.AttemptStatus;
import rw.ac.auca.onlinequiz.repository.FeedbackNoteRepository;
import rw.ac.auca.onlinequiz.repository.InstructorRepository;
import rw.ac.auca.onlinequiz.repository.LabRepository;
import rw.ac.auca.onlinequiz.repository.QuizAttemptRepository;
import rw.ac.auca.onlinequiz.repository.StudentRepository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private FeedbackNoteRepository feedbackNoteRepository;

    public Map<String, Long> getDashboardSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("students", studentRepository.count());
        summary.put("instructors", instructorRepository.count());
        summary.put("freeLabs", (long) labRepository.findFreeLabs().size());
        summary.put("attemptsToday", quizAttemptRepository.countByAttemptDate(LocalDate.now()));
        summary.put("started", quizAttemptRepository.countByStatus(AttemptStatus.STARTED));
        summary.put("feedbackNotes", feedbackNoteRepository.count());
        return summary;
    }
}
