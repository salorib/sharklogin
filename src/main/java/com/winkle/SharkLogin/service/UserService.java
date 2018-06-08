/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winkle.SharkLogin.service;

import com.winkle.SharkAlgorithm.SharkPasswordEncoder;
import com.winkle.SharkLogin.model.Role;
import com.winkle.SharkLogin.model.User;
import com.winkle.SharkLogin.repository.RoleRepository;
import com.winkle.SharkLogin.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Argos
 */
@Service
public class UserService implements IUserService{
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private SharkPasswordEncoder passwordEncoder;
    

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(1);
        Role usrRole = roleRepository.findByRole("USER");
        user.setRoleSet(new HashSet<Role>(Arrays.asList(usrRole)));
        userRepository.save(user);
    }
    
}
