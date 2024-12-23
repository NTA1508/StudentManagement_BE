package com.school.student_mg.services;

import com.school.student_mg.exception.NotFoundException;
import com.school.student_mg.models.Parent;
import com.school.student_mg.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentServiceImp implements ParentService{

    @Autowired
    private ParentRepository parentRepository;

    @Override
    public Parent addParent(Parent parent) {
        return parentRepository.save(parent);
    }

    @Override
    public Parent getParentById(String id) {
        return parentRepository.findById(id).orElseThrow(()-> new NotFoundException(false, "Parent Not Found"));
    }

    @Override
    public Parent updateParent(Parent parent, String id) {
        Parent p = parentRepository.findById(id).orElseThrow(()->new NotFoundException(false, "Parent Not Found"));
        p.setFatherName(parent.getFatherName());
        p.setMotherName(parent.getMotherName());
        p.setFatherPhoneNumber(parent.getFatherPhoneNumber());
        p.setMotherPhoneNumber(parent.getMotherPhoneNumber());
        p.setFatherJob(parent.getFatherJob());
        p.setMotherJob(parent.getMotherJob());
        p.setParentAddress(parent.getParentAddress());
        return parentRepository.save(p);
    }

    @Override
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }
}
