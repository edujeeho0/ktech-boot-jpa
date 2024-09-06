package com.example.jpa.school.repo;

import com.example.jpa.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH s.advisor")
    List<Student> findAllJoinAdvisor();

    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.advisor")
    List<Student> findAllLeftJoinAdvisor();

    @Query("SELECT DISTINCT s FROM Student s RIGHT JOIN FETCH s.advisor")
    List<Student> findAllRightJoinAdvisor();
}
