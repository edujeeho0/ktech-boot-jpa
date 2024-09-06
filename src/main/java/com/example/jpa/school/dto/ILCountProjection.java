package com.example.jpa.school.dto;

import com.example.jpa.school.entity.Instructor;

public interface ILCountProjection {
    /*
    private Instructor instructor;
    private Long lectureCount;
     */

    Instructor getInstructor();
    Long getLectureCount();
}
