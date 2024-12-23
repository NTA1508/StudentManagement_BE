package com.school.student_mg.controllers;

import com.school.student_mg.models.Parent;
import com.school.student_mg.repositories.ParentRepository;
import com.school.student_mg.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/parent")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @Autowired
    private ParentRepository parentRepository;

    public ParentController(ParentService parentService, ParentRepository parentRepository) {
        this.parentService = parentService;
        this.parentRepository = parentRepository;
    }

    public ParentController() {
    }

    public ParentService getParentService() {
        return parentService;
    }

    public void setParentService(ParentService parentService) {
        this.parentService = parentService;
    }

    public ParentRepository getParentRepository() {
        return parentRepository;
    }

    public void setParentRepository(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addParent(@RequestBody Parent parent){
        return new ResponseEntity<Parent>(parentService.addParent(parent), HttpStatus.OK);
    }

    @GetMapping("/parents")
    public ResponseEntity<?> getAllParents(){
        return new ResponseEntity<List<Parent>>(parentService.getAllParents(), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateParent(@RequestBody Parent parent,@PathVariable String id){
        return new ResponseEntity<Parent>(parentService.updateParent(parent, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParentById(@PathVariable String id) {
        return new ResponseEntity<Parent>(parentService.getParentById(id), HttpStatus.OK);
    }
}
