package com.socialnetwork.project.controller;

import com.socialnetwork.project.entity.User;
import com.socialnetwork.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> categories() {
        return new ResponseEntity<>(
                userService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<User> details(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(
                userService.readById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(
                userService.create(user),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        return new ResponseEntity<>(
                userService.update(user),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(
                userService.delete(userService.readById(id)),
                HttpStatus.OK
        );
    }
}
