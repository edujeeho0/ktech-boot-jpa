package com.example.jpa.school.entity;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany(mappedBy = "lectures")
    private final List<Student> students = new ArrayList<>();
}
