package com.example.jpa.school;

import com.example.jpa.school.entity.Lecture;
import com.example.jpa.school.entity.Student;
import com.example.jpa.school.repo.LectureRepository;
import com.example.jpa.school.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("school")
@RequiredArgsConstructor
public class SchoolController {
    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;

    @GetMapping("test/many-to-many")
    public String testMtoN() {
        // 1. 두명의 학생 (alex, brad)를 만든다.
        Student alex = Student.builder()
                .name("alex")
                .build();
        alex = studentRepository.save(alex);
        Student brad = Student.builder()
                .name("brad")
                .build();
        brad = studentRepository.save(brad);

        // 2. 2개의 강의 (jpa, spring boot)를 만든다.
        Lecture jpa = Lecture.builder()
                .name("jpa")
                .day("friday")
                .startTime(9)
                .endTime(16)
                .build();
        jpa = lectureRepository.save(jpa);
        Lecture boot = Lecture.builder()
                .name("spring boot")
                .day("mon-fri")
                .startTime(0)
                .endTime(24)
                .build();
        boot = lectureRepository.save(boot);

        // 3. alex의 lectures에 jpa와 boot를 추가한다.
        alex.getLectures().add(jpa);
        alex.getLectures().add(boot);

        // 4. brad의 lectures에 boot를 추가한다.
        brad.getLectures().add(boot);

        // 5. alex와 spring boot를 각각 저장해본다.
        studentRepository.save(alex);
        lectureRepository.save(boot);

        return "done";
    }

    @GetMapping("test/query")
    public String testQuery() {
        List<Lecture> lectures = lectureRepository
                .findLecturesBeforeLunch();
        for (Lecture lecture : lectures) {
            log.info("{}: {}", lecture.getName(), lecture.getStartTime());
        }

        lectures = lectureRepository.findLecturesByTime(12, 15);
        for (Lecture lecture : lectures) {
            log.info(
                    "{}: {} - {}",
                    lecture.getName(),
                    lecture.getStartTime(),
                    lecture.getEndTime()
            );
        }

        lectures = lectureRepository.findLecturesByTimeNative(12, 15);
        for (Lecture lecture : lectures) {
            log.info(
                    "{}: {} - {}",
                    lecture.getName(),
                    lecture.getStartTime(),
                    lecture.getEndTime()
            );
        }

        lectures = lectureRepository.findLecturesByTimeNamed(11, 14);
        for (Lecture lecture : lectures) {
            log.info(
                    "{}: {} - {}",
                    lecture.getName(),
                    lecture.getStartTime(),
                    lecture.getEndTime()
            );
        }

        lectures = lectureRepository.findLecturesByDay(List.of("mon", "wed", "fri"));
        for (Lecture lecture : lectures) {
            log.info(
                    "{}: {}", lecture.getName(), lecture.getDay()
            );
        }

        Page<Lecture> lecturePage = lectureRepository.findLecturesStartsBefore(
                14,
                PageRequest.of(0, 3)
        );
        log.info(lecturePage.getContent().toString());

        lecturePage = lectureRepository.findLecturesEndBeforeNative(
                17,
                PageRequest.of(0, 3)
        );
        log.info(String.valueOf(lecturePage.getContent().size()));

        return "done";
    }

    @Transactional
    @GetMapping("test/modifying")
    public String testModifying() {
        log.info(lectureRepository.sackInstructorNotAdvising().toString());
        return "done";
    }
}
