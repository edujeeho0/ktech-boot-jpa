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
public class Student extends BaseEntity {
    private String name;
    private Integer age;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Instructor advisor;

    @ManyToMany
    @JoinTable(
            name = "attending_lectures",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id")
    )
    private final List<Lecture> lectures = new ArrayList<>();
}
