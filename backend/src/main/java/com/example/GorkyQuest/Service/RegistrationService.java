package com.example.GorkyQuest.Service;

import com.example.GorkyQuest.Model.User;
import com.example.GorkyQuest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public String registerUser(User user) {
        // Проверка на существующий email
        if (userRepository.existsByEmail(user.getEmail())) {
           return  "Пользователь с такой почтой уже существует";
        }

        // Сохранение нового пользователя
        userRepository.save(user);
        return "Пользователь успешно добавлен";
    }

}
