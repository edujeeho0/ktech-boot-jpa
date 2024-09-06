package com.example.jpa.school.entity;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "advisor", fetch = FetchType.LAZY)
    private final List<Student> advisingStudents
            = new ArrayList<>();

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private final List<Lecture> lectures
            = new ArrayList<>();
}
