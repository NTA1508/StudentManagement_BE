package com.school.student_mg.controllers;

import com.school.student_mg.dto.LecturerDto;
import com.school.student_mg.models.AuthenticationResponse;
import com.school.student_mg.models.Lecturer;
import com.school.student_mg.models.Parent;
import com.school.student_mg.repositories.LectureRepository;
import com.school.student_mg.services.AuthenticationService;
import com.school.student_mg.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping("/api/lecturer")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/add")
    public ResponseEntity<AuthenticationResponse> addLecturer(@RequestBody Lecturer lecturer){
        return ResponseEntity.ok(lecturerService.addLecturer(lecturer));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Lecturer lecturer){
        return ResponseEntity.ok(authenticationService.login(lecturer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLecturerById(@PathVariable String id){
        return new ResponseEntity<LecturerDto>(lecturerService.getLecturerById(id), HttpStatus.OK);
    }

    @GetMapping("/lecturers")
    public ResponseEntity<?> getAllLecturer(){
        return  new ResponseEntity<List<LecturerDto>>(lecturerService.getAllLecturer(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLecturer(@RequestBody Lecturer request, @PathVariable String id){
        Optional<Lecturer> lecturer = lectureRepository.findById(id);
        if (lecturer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Lecturer with ID " + id + " not found");
        }
        lecturerService.updateLecturer(request, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Update Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLecturer(@PathVariable String id) throws IOException {
        Optional<Lecturer> lecturer = lectureRepository.findById(id);
        if (lecturer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Lecturer with ID " + id + " not found");
        }
        lecturerService.deleteLecturer(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Delete Successfully");
    }
}
