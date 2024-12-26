package com.school.student_mg.services;

import com.school.student_mg.dto.FacultyDto;
import com.school.student_mg.dto.LecturerDto;
import com.school.student_mg.exception.NotFoundException;
import com.school.student_mg.models.Faculty;
import com.school.student_mg.models.Lecturer;
import com.school.student_mg.models.Role;
import com.school.student_mg.repositories.FacultyRepository;
import com.school.student_mg.repositories.LectureRepository;
import com.school.student_mg.repositories.RoleRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImp implements FacultyService{

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Faculty addFaculty(Faculty faculty, String lecturerId) {

        Lecturer lecturer = lectureRepository.findById(lecturerId).orElseThrow(()-> new NotFoundException(false, "Lecturer Not Found"));

        Role additionalRole = roleRepository.findByRoleName("ROLE_DEAN")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRoleName("ROLE_DEAN");
                    return roleRepository.save(newRole);
                });
        Set<Role> roles = lecturer.getRoles();
        roles.add(additionalRole);
        String currentStatus = lecturer.getStatus();
        if (currentStatus == null || !currentStatus.contains("Dean")) {
            lecturer.setStatus((currentStatus == null ? "" : currentStatus + ", ") + "Dean");
        }


        faculty.setDean(lecturer);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty, String id) {
        Faculty f = facultyRepository.findById(id).orElseThrow(()->new NotFoundException(false, "Faculty not found"));

        f.setFacultyName(faculty.getFacultyName());
        f.setFacultyRoom(faculty.getFacultyRoom());
        f.setEmail(faculty.getEmail());
        f.setFoundingDate(faculty.getFoundingDate());
        f.setPhoneNumber(faculty.getPhoneNumber());

        Lecturer lecturer = lectureRepository.findById(id).orElseThrow(()-> new NotFoundException(false, "Lecturer not found"));

        f.setDean(lecturer);
        return facultyRepository.save(f);
    }

    @Override
    public Faculty getFacultyById(String id) {
        return facultyRepository.findById(id).orElseThrow(()->new NotFoundException(false, "Faculty not found"));
    }

//    @Override
//    public List<FacultyDto> getAllFaculty() {
//        return List.of();
//    }

    @Override
    public List<FacultyDto> getAllFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(FacultyDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFaculty(String id) {

    }
}
