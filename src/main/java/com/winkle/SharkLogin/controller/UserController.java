/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winkle.SharkLogin.controller;


import com.winkle.SharkLogin.model.User;
import com.winkle.SharkLogin.repository.UserRepository;
import com.winkle.SharkLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Argos
 */
@Controller
@RequestMapping
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;
    

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/signup")
    public String signin(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/signup/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        model.addAttribute("user", user);
        
        if(userRepository.findByEmail(user.getEmail()) == null){
            service.saveUser(user);
        }else{
            System.out.println("Email already registered");
            model.addAttribute("error", "email already registered");
            return "signup";
        }
        
        return "register";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @RequestMapping("/user")
    public String user(Model model) {
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", user.getUsername());
        return "user/user";
    }

}
