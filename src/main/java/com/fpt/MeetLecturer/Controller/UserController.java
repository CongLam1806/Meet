package com.fpt.MeetLecturer.Controller;

import com.fpt.MeetLecturer.EntityModel.User;
import com.fpt.MeetLecturer.Service.UserService;
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
    public User createNew(@RequestBody User model){
        return
    }
}
