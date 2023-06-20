package com.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private String id;
    private LocalDateTime createdAt;
    private String reviewerId;
    private int value;
}
