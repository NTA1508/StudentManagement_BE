package com.school.student_mg.services;

import com.school.student_mg.exception.NotFoundException;
import com.school.student_mg.models.Lecturer;
import com.school.student_mg.models.Role;
import com.school.student_mg.repositories.LectureRepository;
import com.school.student_mg.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Lecturer lecturer = lectureRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException(false, "Lecturer Not Found"));

        List<Role> roles = roleRepository.findByLecturers_Email(email);
        if(roles.isEmpty()){
            System.out.println("No roles found");
        }
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        System.out.println("Granted Authorities" + authorities);
        return new User(
                lecturer.getEmail(),
                lecturer.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
