package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseDTO getUser(){
        return userService.getUser();
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @PostMapping("post")
    public ResponseDTO createUser(@RequestBody UserDTO model){
        return userService.createUser(model);
    }
    @PutMapping("/put/{id}")
    public ResponseDTO updateUser(@RequestBody UserDTO model, @PathVariable("id") int id){

        return userService.updateUser(model);

    }
    @DeleteMapping("/delete-user")
    public boolean deleteUser(@RequestParam(value = "id") int id) {
      return userService.deleteUser(id);
  }
}
