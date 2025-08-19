package com.booleanuk.api.controller;

import com.booleanuk.api.model.User;
import com.booleanuk.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repo;


    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(this.repo.findAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
         User savedUser = this.repo.save(user);
         return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> putOne(@PathVariable int id, @RequestBody User user){
        User toBeEdited = this.repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        toBeEdited.setEmail(user.getEmail());
        toBeEdited.setPhone(user.getPhone());
        toBeEdited.setUsername(user.getUsername());
        toBeEdited.setFirstName(user.getFirstName());
        toBeEdited.setLastName(user.getLastName());

        return new ResponseEntity<>(this.repo.save(toBeEdited), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteOne(@PathVariable int id){
        User deleteUser = this.repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
        this.repo.delete(deleteUser);
        return ResponseEntity.ok(deleteUser);
    }
}
