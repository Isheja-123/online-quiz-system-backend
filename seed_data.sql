-- Seed Data for Online Quiz System (Step 0)
-- Database: quizdb
-- Student: Umuhire Isheja Delice (ID: 27832)

-- 1. Insert 4 Subjects (Web Technology, Databases, Networking, Mathematics)
-- (Nobody holds Mathematics)
INSERT INTO subject (id, name) VALUES 
('11111111-1111-1111-1111-111111111111', 'Web Technology'),
('22222222-2222-2222-2222-222222222222', 'Databases'),
('33333333-3333-3333-3333-333333333333', 'Networking'),
('44444444-4444-4444-4444-444444444444', 'Mathematics')
ON CONFLICT (id) DO NOTHING;

-- 2. Insert 4 Labs (numbered 1, 2, 3, 4)
-- (Lab 4 has no instructor)
INSERT INTO lab (id, lab_number, name) VALUES 
('aaaaaaaa-1111-1111-1111-aaaaaaaaaaaa', 1, 'Lab 1'),
('aaaaaaaa-2222-2222-2222-aaaaaaaaaaaa', 2, 'Lab 2'),
('aaaaaaaa-3333-3333-3333-aaaaaaaaaaaa', 3, 'Lab 3'),
('aaaaaaaa-4444-4444-4444-aaaaaaaaaaaa', 4, 'Lab 4')
ON CONFLICT (id) DO NOTHING;

-- 3. Insert 3 Instructors (each with a lab)
INSERT INTO instructor (id, first_name, last_name, email, lab_id) VALUES 
('bbbbbbbb-1111-1111-1111-bbbbbbbbbbbb', 'Patrick', 'Dushimimana', 'patrick@auca.ac.rw', 'aaaaaaaa-1111-1111-1111-aaaaaaaaaaaa'),
('bbbbbbbb-2222-2222-2222-bbbbbbbbbbbb', 'Jean', 'Mugisha', 'jean.mugisha@auca.ac.rw', 'aaaaaaaa-2222-2222-2222-aaaaaaaaaaaa'),
('bbbbbbbb-3333-3333-3333-bbbbbbbbbbbb', 'Marie', 'Uwase', 'marie.uwase@auca.ac.rw', 'aaaaaaaa-3333-3333-3333-aaaaaaaaaaaa')
ON CONFLICT (id) DO NOTHING;

-- Instructor-Subject Links:
-- Instructor 1 holds two subjects (Web Technology & Databases)
-- Instructor 2 holds exactly one (Networking)
-- Instructor 3 holds Web Technology
INSERT INTO instructor_subject (instructor_id, subject_id) VALUES 
('bbbbbbbb-1111-1111-1111-bbbbbbbbbbbb', '11111111-1111-1111-1111-111111111111'),
('bbbbbbbb-1111-1111-1111-bbbbbbbbbbbb', '22222222-2222-2222-2222-222222222222'),
('bbbbbbbb-2222-2222-2222-bbbbbbbbbbbb', '33333333-3333-3333-3333-333333333333'),
('bbbbbbbb-3333-3333-3333-bbbbbbbbbbbb', '11111111-1111-1111-1111-111111111111')
ON CONFLICT DO NOTHING;

-- 4. Insert 5 Students (Two share lastName 'Isheja', one with regNumber 27832)
INSERT INTO student (id, first_name, last_name, reg_number, email) VALUES 
('cccccccc-1111-1111-1111-cccccccccccc', 'Delice', 'Isheja', '27832', 'delice.isheja@auca.ac.rw'),
('cccccccc-2222-2222-2222-cccccccccccc', 'Aline', 'Isheja', '27833', 'aline.isheja@auca.ac.rw'),
('cccccccc-3333-3333-3333-cccccccccccc', 'Eric', 'Habimana', '27834', 'eric.habimana@auca.ac.rw'),
('cccccccc-4444-4444-4444-cccccccccccc', 'Grace', 'Mutoni', '27835', 'grace.mutoni@auca.ac.rw'),
('cccccccc-5555-5555-5555-cccccccccccc', 'Kevin', 'Kagame', '27836', 'kevin.kagame@auca.ac.rw')
ON CONFLICT (id) DO NOTHING;

-- 5. Insert 10 Quiz Attempts (spread over October & November 2026, using all 4 statuses)
-- Student 27832 has at least 3 attempts
-- Instructor 1 has two attempts on 2026-10-20 (one ABANDONED)
-- At least 2 attempts are GRADED
INSERT INTO quiz_attempt (id, attempt_date, status, quiz_title, score, student_id, instructor_id) VALUES 
('dddddddd-0001-0001-0001-dddddddddddd', '2026-10-15', 'STARTED', 'HTML Basics Quiz', 70, 'cccccccc-1111-1111-1111-cccccccccccc', 'bbbbbbbb-1111-1111-1111-bbbbbbbbbbbb'),
('dddddddd-0002-0002-0002-dddddddddddd', '2026-10-20', 'ABANDONED', 'CSS Styling Quiz', 40, 'cccccccc-1111-1111-1111-cccccccccccc', 'bbbbbbbb-1111-1111-1111-bbbbbbbbbbbb'),
('dddddddd-0003-0003-0003-dddddddddddd', '2026-10-20', 'SUBMITTED', 'JavaScript DOM Quiz', 80, 'cccccccc-2222-2222-2222-cccccccccccc', 'bbbbbbbb-1111-1111-1111-bbbbbbbbbbbb'),
('dddddddd-0004-0004-0004-dddddddddddd', '2026-10-25', 'GRADED', 'SQL Queries Quiz', 92, 'cccccccc-1111-1111-1111-cccccccccccc', 'bbbbbbbb-2222-2222-2222-bbbbbbbbbbbb'),
('dddddddd-0005-0005-0005-dddddddddddd', '2026-10-28', 'GRADED', 'Database Schema Quiz', 88, 'cccccccc-3333-3333-3333-cccccccccccc', 'bbbbbbbb-2222-2222-2222-bbbbbbbbbbbb'),
('dddddddd-0006-0006-0006-dddddddddddd', '2026-11-02', 'STARTED', 'Network Protocols Quiz', 65, 'cccccccc-4444-4444-4444-cccccccccccc', 'bbbbbbbb-2222-2222-2222-bbbbbbbbbbbb'),
('dddddddd-0007-0007-0007-dddddddddddd', '2026-11-05', 'STARTED', 'Spring Boot API Quiz', 75, 'cccccccc-5555-5555-5555-cccccccccccc', 'bbbbbbbb-3333-3333-3333-bbbbbbbbbbbb'),
('dddddddd-0008-0008-0008-dddddddddddd', '2026-11-10', 'SUBMITTED', 'JPA Relationships Quiz', 85, 'cccccccc-1111-1111-1111-cccccccccccc', 'bbbbbbbb-3333-3333-3333-bbbbbbbbbbbb'),
('dddddddd-0009-0009-0009-dddddddddddd', '2026-11-12', 'STARTED', 'Security & Filters Quiz', 60, 'cccccccc-2222-2222-2222-cccccccccccc', 'bbbbbbbb-1111-1111-1111-bbbbbbbbbbbb'),
('dddddddd-0010-0010-0010-dddddddddddd', '2026-11-15', 'ABANDONED', 'REST Controllers Quiz', 50, 'cccccccc-3333-3333-3333-cccccccccccc', 'bbbbbbbb-2222-2222-2222-bbbbbbbbbbbb')
ON CONFLICT (id) DO NOTHING;

-- 6. Insert Feedback Notes for GRADED Quiz Attempts (Part E)
INSERT INTO feedback_note (id, question_topic, comment, marks_awarded, given_date, quiz_attempt_id) VALUES 
('eeeeeeee-1111-1111-1111-eeeeeeeeeeee', 'SQL Joins', 'Well structured query using inner join', 5, '2026-10-26', 'dddddddd-0004-0004-0004-dddddddddddd'),
('eeeeeeee-2222-2222-2222-eeeeeeeeeeee', 'Normalization', 'Good explanation of 3NF', 4, '2026-10-29', 'dddddddd-0005-0005-0005-dddddddddddd')
ON CONFLICT (id) DO NOTHING;
