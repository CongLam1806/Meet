package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<UserDTO> getUser(){
        return userService.get();
    }

    @GetMapping("/get/{id}")
    public UserDTO getById(@PathVariable("id") int id){
        return userService.getById(id);
    }
    @PostMapping("post")
    public void createUser(@RequestBody UserDTO model){

    }
    @PutMapping("/put/{id}")
    public void createUser(@RequestBody UserDTO model, @PathVariable("id") int id){

        userService.updateUser(model);

    }
  @DeleteMapping("/delete-user")
    public boolean deleteUser(@RequestParam(value = "id") int id) {
      return userService.deleteUser(id);
  }
}
