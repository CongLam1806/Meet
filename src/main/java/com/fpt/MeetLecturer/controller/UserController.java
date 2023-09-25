package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.BusinessModel.UserDTO;
import com.fpt.MeetLecturer.EntityModel.User;
import com.fpt.MeetLecturer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> getUser(){
        return userService.get();
    }

    @PostMapping("/user/post")
    public UserDTO updateUser(@RequestBody User model, @PathVariable int id){
        return userService.updateUser(model,id);
    }
    @DeleteMapping("/user/delete/{id}")
    public boolean deleteUser(@RequestBody User user, @PathVariable int id){
        return userService.deleteUser(user, id);
    }
}
