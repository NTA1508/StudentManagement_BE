package com.school.student_mg.controllers;

import com.school.student_mg.models.AuthenticationResponse;
import com.school.student_mg.models.Lecturer;
import com.school.student_mg.repositories.LectureRepository;
import com.school.student_mg.services.AuthenticationService;
import com.school.student_mg.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/lecturer/add")
    public ResponseEntity<AuthenticationResponse> addLecturer(@RequestBody Lecturer lecturer){
        return ResponseEntity.ok(lecturerService.addLecturer(lecturer));
    }

    @PostMapping("/lecturer/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Lecturer lecturer){
        return ResponseEntity.ok(authenticationService.login(lecturer));
    }
}
