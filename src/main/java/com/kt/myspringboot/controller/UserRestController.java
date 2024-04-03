package com.kt.myspringboot.controller;

import com.kt.myspringboot.entity.User;
import com.kt.myspringboot.exception.BusinessException;
import com.kt.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserRepository userRepository;

    //Constructor Injection
//    public UserRestController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(
                () -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND)
        );
        return user;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PatchMapping("/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User userDetail){
        User existUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        existUser.setName(userDetail.getName());
        return userRepository.save(existUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
        return ResponseEntity.ok( id + " User가 삭제 되었습니다.");
    }

}
