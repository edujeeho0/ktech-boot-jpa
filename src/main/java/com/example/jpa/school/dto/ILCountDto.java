package com.example.jpa.school.dto;


import com.example.jpa.school.entity.Instructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ILCountDto {
    private Instructor instructor;
    private Long count;
}
