package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getUser(){

        return ResponseEntity.ok().body(userService.getUser()) ;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable("id") int id){

        return ResponseEntity.ok().body(userService.getUserById(id)) ;
    }

    @PostMapping("post")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO model){
        return ResponseEntity.ok().body(userService.createUser(model));
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO model, @PathVariable("id") int id){

        return ResponseEntity.ok().body(userService.updateUser(model));

    }
    @DeleteMapping("/delete-user")
    public boolean deleteUser(@RequestParam(value = "id") int id) {
      return userService.deleteUser(id);
  }
}
