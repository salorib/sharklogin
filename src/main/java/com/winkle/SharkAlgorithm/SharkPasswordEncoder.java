/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winkle.SharkAlgorithm;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Argos
 */
public class SharkPasswordEncoder implements PasswordEncoder{
    
    
    @Override
    public String encode(CharSequence cs) {
        String test = Shark.init((String) cs);
        return test;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String test = Shark.init((String) rawPassword);
        return test.equals(encodedPassword);
    }
    
}
