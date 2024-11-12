package com.example.GorkyQuest.Service;

import com.example.GorkyQuest.Exception.UserRegistrationException;
import com.example.GorkyQuest.Model.User;
import com.example.GorkyQuest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]" +
            "+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    @Autowired
    public RegistrationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }



    public String registerUser(User user) {
        // Проверка на существующий email
        if (userRepository.existsByEmail(user.getEmail())) {
           throw new UserRegistrationException("Пользователь с такой почтой уже существует");
        }

        if (!Pattern.matches(EMAIL_PATTERN, user.getEmail())) {
            throw new UserRegistrationException("Некорректный формат электронной почты");
        }
        // Сохранение нового пользователя
        userRepository.save(user);
        return "Пользователь успешно добавлен";
    }

}
