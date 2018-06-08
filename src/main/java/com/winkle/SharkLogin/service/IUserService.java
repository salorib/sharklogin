/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winkle.SharkLogin.service;

import com.winkle.SharkLogin.model.User;

/**
 *
 * @author Argos
 */
public interface IUserService {
    public User findByEmail(String email);
    public void saveUser(User user);
}
