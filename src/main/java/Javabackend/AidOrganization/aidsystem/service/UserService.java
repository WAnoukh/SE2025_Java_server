package com.example.aidsystem.service;

import com.example.aidsystem.model.User;
import com.example.aidsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender emailSender;

    public void registerUser(User user) {
        // Хеширование пароля перед сохранением в базе данных
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Сохранение данных пользователя в базе данных
        userRepository.save(user);

        // Отправка письма с подтверждением
        sendConfirmationEmail(user);
    }

    private void sendConfirmationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo((String) user.getEmail());
        message.setSubject("Confirm your registration");
        message.setText("Click here to confirm your registration.");
        emailSender.send(message);
    }
}
