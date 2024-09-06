package com.example.jpa.school.entity;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecture extends BaseEntity {
    private String name;
    private String day;
    private Integer startTime;
    private Integer endTime;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany(mappedBy = "lectures", cascade = CascadeType.ALL)
    private final List<Student> students = new ArrayList<>();
}
