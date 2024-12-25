package com.school.student_mg.services;

import com.school.student_mg.dto.LecturerDto;
import com.school.student_mg.exception.NotFoundException;
import com.school.student_mg.models.AuthenticationResponse;
import com.school.student_mg.models.Lecturer;
import com.school.student_mg.models.Role;
import com.school.student_mg.repositories.LectureRepository;
import com.school.student_mg.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImp implements LecturerService{

    private final LectureRepository lectureRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public LecturerServiceImp(LectureRepository lectureRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtService jwtService) {
        this.lectureRepository = lectureRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
    }

    //Sửa lại
    @Override
    public AuthenticationResponse addLecturer(Lecturer request) {
        if(request.getEmail() == null || request.getPassword() == null){
            throw new IllegalArgumentException("Email password required");
        }

        Lecturer lecturer = new Lecturer();
        lecturer.setEmail(request.getEmail());
        Optional<Lecturer> existingEmail = lectureRepository.findByEmail(lecturer.getEmail());
        if(existingEmail.isPresent()){
            throw  new RuntimeException("Email is already in use");
        }
        lecturer.setFirstName(request.getFirstName());
        lecturer.setLastName(request.getLastName());
        lecturer.setGender(request.getGender());
        lecturer.setDob(request.getDob());
        lecturer.setPhoneNumber(request.getPhoneNumber());
        lecturer.setAddress(request.getAddress());
        lecturer.setStatus(request.getStatus());
        lecturer.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository.findByRoleName("ROLE_LECTURER")
                .orElseGet(()->{
                    Role newRole = new Role();
                    newRole.setRoleName("ROLE_LECTURER");
                    return roleRepository.save(newRole);
                });
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        lecturer.setRoles(roles);

        lecturer = lectureRepository.save(lecturer);

        String token = jwtService.generateToken(lecturer);
        return new AuthenticationResponse(token);
    }

    @Override
    public LecturerDto getLecturerById(String id) {
        Lecturer lecturer = lectureRepository.findById(id).orElseThrow(()->new NotFoundException(false, "Lecturer not found"));
        return new LecturerDto(lecturer);
    }

    @Override
    public Lecturer updateLecturer(Lecturer request, String id) {
        Lecturer lecturer = lectureRepository.findById(id).orElseThrow(()-> new NotFoundException(false, "Lecturer Not Found"));

        lecturer.setEmail(request.getEmail());
        Optional<Lecturer> existingEmail = lectureRepository.findByEmail(lecturer.getEmail());

        if (!request.getEmail().equals(lecturer.getEmail())){
            if(existingEmail.isPresent()){
                throw  new RuntimeException("Email is already in use");
            }
        }

        lecturer.setFirstName(request.getFirstName());
        lecturer.setLastName(request.getLastName());
        lecturer.setGender(request.getGender());
        lecturer.setDob(request.getDob());
        lecturer.setPhoneNumber(request.getPhoneNumber());
        lecturer.setAddress(request.getAddress());
        lecturer.setStatus(request.getStatus());
        lecturer.setPassword(passwordEncoder.encode(request.getPassword()));

        return lectureRepository.save(lecturer);
    }

    @Override
    public List<LecturerDto> getAllLecturer() {
        return lectureRepository.findAll()
                .stream()
                .map(LecturerDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLecturer(String id) {
        Lecturer lecturer = lectureRepository.findById(id).orElseThrow(()-> new NotFoundException(false, "Lecturer not found"));
        lectureRepository.delete(lecturer);
    }
}
