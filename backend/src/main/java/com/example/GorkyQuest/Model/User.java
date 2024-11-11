package com.example.GorkyQuest.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users",schema = "gorkyquest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username",nullable = false)
    private String UserName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash",nullable = false)
    private String password;

}
