package com.finances.finT.Controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.finances.finT.models.users;
import com.finances.finT.repositories.UserRepository;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(
        @RequestParam String username,
        @RequestParam String password,
        @RequestParam String email,
        Model model
    ) {
        // Проверка существования пользователя
        if (userRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Имя пользователя уже занято");
            return "redirect:/register";
        }
        
        users newUser = new users();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // Кодирование пароля
        newUser.setEmail(email);
        userRepository.save(newUser);
        
        model.addAttribute("username", username);
        return "redirect:/login?registered=true"; 
    }


}