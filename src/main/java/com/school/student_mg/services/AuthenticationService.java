package com.school.student_mg.services;

import com.school.student_mg.exception.NotFoundException;
import com.school.student_mg.models.AuthenticationResponse;
import com.school.student_mg.models.Lecturer;
import com.school.student_mg.repositories.LectureRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final LectureRepository lectureRepository;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService, LectureRepository lectureRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.lectureRepository = lectureRepository;
    }

    public AuthenticationResponse login(Lecturer request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Lecturer lecturer = lectureRepository.findByEmail(request.getEmail()).orElseThrow(()->new NotFoundException(false, "User not found"));
        String token = jwtService.generateToken(lecturer);
        return new AuthenticationResponse(token);
    }
}
