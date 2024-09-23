package com.example.jpa.school.repo;

import com.example.jpa.school.dto.ILCountDto;
import com.example.jpa.school.dto.ILCountProjection;
import com.example.jpa.school.entity.Instructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    // 데이터의 변경이 일어날 수 있는 Query의 경우
    // Modifying을 추가해야 한다.
    @Modifying
    @Query("DELETE FROM Instructor i " +
            "WHERE size(i.advisingStudents) = 0")
    // 반환되는 Integer는 영향받은 줄의 갯수
    Integer sackInstructorNotAdvising();


    // 집계함수
    // 강사 당 강의하고 있는 강의 갯수
    /*
    SELECT i.name, COUNT(*)
    FROM instructor i INNER JOIN lecture l ON i.id = l.instructor_id
    GROUP BY i.name
     */
    @Query("SELECT l.instructor, COUNT(*) FROM Lecture l " +
            "GROUP BY l.instructor")
    List<Object[]> selectILCountObject();

    @Query("SELECT new com.example.jpa.school.dto.ILCountDto(l.instructor, COUNT(*)) " +
            "FROM Lecture l " +
            "GROUP BY l.instructor")
    List<ILCountDto> selectILCountDto();

    @Query("SELECT l.instructor AS instructor, COUNT(*) AS lectureCount " +
            "FROM Lecture l " +
            "GROUP BY l.instructor")
    List<ILCountProjection> selectILCountProj();


    @EntityGraph(attributePaths = {"advisingStudents"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT DISTINCT i FROM Instructor i")
    List<Instructor> findByEntityGraph();

    // Multi-Bag Fetch (문제)
    @EntityGraph(
            attributePaths = {
                    "advisingStudents",
                    "lectures"
            },
            type = EntityGraph.EntityGraphType.FETCH
    )
    @Query("SELECT DISTINCT i FROM Instructor i")
    List<Instructor> findWithStudentAndLecture();
}








