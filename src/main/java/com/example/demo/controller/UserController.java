package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Kayıt başarılı olduğunda 201 Created dönmek daha doğrudur
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        // GELEN VERİYİ KONTROL ET
        System.out.println("Giriş denemesi: " + loginRequest.getEmail());
        System.out.println("Gelen şifre: " + loginRequest.getPassword());
        // Repository'den email ile kullanıcıyı buluyoruz
        User user = userRepository.findByEmail(loginRequest.getEmail());

        // Kullanıcı var mı ve şifre eşleşiyor mu kontrolü
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            // Giriş başarılı: Kullanıcı nesnesini ve 200 OK dönüyoruz
            return ResponseEntity.ok(user);
        } else {
            // Giriş başarısız: Hata mesajı ve 401 Unauthorized dönüyoruz
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Hatalı email veya şifre!"));
        }
    }
}