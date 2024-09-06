package com.example.jpa.school.entity;

import com.example.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Instructor extends BaseEntity {
    private String name;
}
