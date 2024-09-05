package com.example.jpa.school.repo;

import com.example.jpa.school.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {}
