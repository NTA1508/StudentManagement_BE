package com.school.student_mg.services;

import com.school.student_mg.models.Parent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParentService{
    public Parent addParent(Parent parent);
    public Parent getParentById(String id);
    public Parent updateParent(Parent parent, String id);
    public List<Parent> getAllParents();
}
