package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.User;
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

    
    @PutMapping("/user/{id}")
    public UserDTO createNew(@RequestBody UserDTO model, @PathVariable("id") int id){
        model.setId(id);
        return userService.updateUser(model);

    }
  @DeleteMapping("/delete-user")
    public boolean deleteUser(@RequestParam(value = "id") int id){
        return userService.deleteUser(id);

}
