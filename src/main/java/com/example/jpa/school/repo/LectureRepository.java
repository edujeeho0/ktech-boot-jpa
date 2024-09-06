package com.example.jpa.school.repo;

import com.example.jpa.school.entity.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    // Query Method는 시작은 간단히 시작하지만
    // 만들다 보면 메서드 이름이 너무 길어진다.
    /*
    SELECT * FROM lecture WHERE start_time > 9 AND end_time < 13;
     */
    List<Lecture> findAllByStartTimeGreaterThanAndEndTimeLessThan(Integer startTime, Integer endTime);

    // Query를 사용하면 JPQL을 전달할 수 있다.
    @Query("SELECT l FROM Lecture l WHERE l.startTime < 12")
    List<Lecture> findLecturesBeforeLunch();
    // SELECT * FROM lecture l WHERE l.start_time < 12

    // nativeQuery 설정을 해주면 SQL을 사용할수도 있다.
    @Query(
            value = "SELECT * FROM lecture l WHERE l.start_time < 12",
            nativeQuery = true
    )
    List<Lecture> findLecturesBeforeLunchNative();


    // 매개변수 전달하기
    @Query("SELECT l FROM Lecture l " +
            "WHERE l.startTime = ?1 AND l.endTime = ?2")
    List<Lecture> findLecturesByTime(Integer startTime, Integer endTime);

    @Query(
            value = "SELECT * FROM lecture " +
                    "WHERE start_time = ?1 AND end_time = ?2",
            nativeQuery = true
    )
    List<Lecture> findLecturesByTimeNative(Integer startTime, Integer endTime);

    // 매개변수 전달하기 Named 버전
    @Query("SELECT l FROM Lecture l " +
            "WHERE l.startTime = :start AND l.endTime = :end")
    List<Lecture> findLecturesByTimeNamed(
            @Param("start") Integer startTime,
            @Param("end") Integer endTime
    );

    // IN 절에 사용할 매개변수는 Collection으로
    @Query("SELECT l FROM Lecture l WHERE l.day IN :days")
    List<Lecture> findLecturesByDay(
            @Param("days") Collection<String> days
    );

    // Pagination
    @Query("SELECT l FROM Lecture l WHERE l.startTime < :start")
    Page<Lecture> findLecturesStartsBefore(
            @Param("start") Integer startTime,
            Pageable pageable
    );

    @Query(
            value = "SELECT * FROM lecture WHERE end_time < :end",
            countQuery = "SELECT COUNT(*) FROM lecture WHERE end_time < :end",
            nativeQuery = true
    )
    Page<Lecture> findLecturesEndBeforeNative(
            @Param("end") Integer endTime,
            Pageable pageable
    );

    // 데이터의 변경이 일어날 수 있는 Query의 경우
    // Modifying을 추가해야 한다.
    @Modifying
    @Query("DELETE FROM Instructor i " +
            "WHERE size(i.advisingStudents) = 0")
    // 반환되는 Integer는 영향받은 줄의 갯수
    Integer sackInstructorNotAdvising();
}
