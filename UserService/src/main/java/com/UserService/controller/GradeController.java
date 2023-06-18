package com.UserService.controller;

import com.UserService.model.Grade;
import com.UserService.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/host-grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/{hostId}")
    public ResponseEntity create(@RequestBody Grade grade, @PathVariable("hostId") String hostId){
        gradeService.create(grade, hostId);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
