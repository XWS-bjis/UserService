package com.UserService.service;

import com.UserService.model.Grade;
import com.UserService.model.User;
import com.UserService.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService {

    private final UserRepository userRepository;

    public GradeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(Grade grade, String hostId){
        User host = userRepository.findById(hostId).orElse(null);
        if(host != null){
            List<Grade> hostGrades = host.getGrades();
            if(hostGrades == null) hostGrades = new ArrayList<>();
            if(userGradeAlreadyExists(hostGrades, grade.getReviewerId())) return;
            grade.setCreatedAt(LocalDateTime.now());
            hostGrades.add(grade);
            host.setGrades(hostGrades);
            host.setAvgGrade(calculateAvgGrade(hostGrades));
            userRepository.save(host);

        }
    }

    private Double calculateAvgGrade(List<Grade> hostGrades) {
        double total = 0;
        for(var grade: hostGrades){
            total += grade.getValue();
        }
        return total/hostGrades.size();
    }

    private boolean userGradeAlreadyExists(List<Grade> grades, String userId){
        boolean userGradeExist = false;
        for(int i = 0; i < grades.size(); i++){
            if(grades.get(i).getReviewerId().equals(userId)) userGradeExist = true;
        }
        return userGradeExist;
    }
}
