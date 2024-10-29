package com.example.GorkyQuest.Service;

import com.example.GorkyQuest.Exception.UserAuthorizationException;
import com.example.GorkyQuest.Model.User;
import com.example.GorkyQuest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthInService {
    private final UserRepository userRepository;

    @Autowired
    public AuthInService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String Authorization(String email,String password) {

        // Проверка на существующий email
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserAuthorizationException("Пользователь с такой почтой не найден"));

        if (!user.getPassword().equals(password)) {
            throw new UserAuthorizationException("Неверный пароль");
        }


        return "Успешный вход";
    }
}
