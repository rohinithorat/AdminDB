package com.quest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quest.model.User;
import com.quest.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	 @Autowired
	    private UserRepository userRepository;
	     
	    @GetMapping("")
	    public String viewHomePage() {
	        return "index";
	    }
	    
	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	        model.addAttribute("user", new User());
	         
	        return "signup_form";
	    }
	
	    @GetMapping("/users")
	    public String listUsers(Model model) {
	        List<User> listUsers = userRepository.findAll();
	        model.addAttribute("listUsers", listUsers);
	         
	        return "users";
	    }

	    @PostMapping("/process_register")
	    public String processRegister(User user) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);
	         
	        userRepository.save(user);
	         
	        return "register_success";
	    }
}
