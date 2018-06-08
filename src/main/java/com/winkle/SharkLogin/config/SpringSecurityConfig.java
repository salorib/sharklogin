/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winkle.SharkLogin.config;

import com.winkle.SharkAlgorithm.SharkPasswordEncoder;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Argos
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private SharkPasswordEncoder encoder;

    private final String usersQuery = "SELECT user.email, user.password, user.status FROM user WHERE user.email=?";
    private final String rolesQuery = "SELECT user.email, role.`role` FROM user INNER JOIN user_roles ON user_roles.id_user = user.id_user INNER JOIN role ON user_roles.id_role = role.id_role WHERE user.email=?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/signup/**").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and()
                .formLogin()
                .usernameParameter("email").passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**");
    }

    @Bean
    public SharkPasswordEncoder passwordEncoder(){
        SharkPasswordEncoder sharkPasswordEncoder = new SharkPasswordEncoder();
        return sharkPasswordEncoder;
    }

}
