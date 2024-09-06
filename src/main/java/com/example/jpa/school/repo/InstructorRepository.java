package com.example.jpa.school.repo;

import com.example.jpa.school.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    // 데이터의 변경이 일어날 수 있는 Query의 경우
    // Modifying을 추가해야 한다.
    @Modifying
    @Query("DELETE FROM Instructor i " +
            "WHERE size(i.advisingStudents) = 0")
    // 반환되는 Integer는 영향받은 줄의 갯수
    Integer sackInstructorNotAdvising();
}
