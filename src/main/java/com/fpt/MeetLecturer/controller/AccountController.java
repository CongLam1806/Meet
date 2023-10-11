package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.AccountDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;

import com.fpt.MeetLecturer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1/user")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAccount(){

        return ResponseEntity.ok().body(accountService.getAccount()) ;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getAccountById(@PathVariable("id") String id){

        return ResponseEntity.ok().body(accountService.getAccountById(id)) ;
    }


    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody AccountDTO model){
        return ResponseEntity.ok().body(accountService.createUser2(model));
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<ResponseDTO> updateAccount(@RequestBody AccountDTO model, @PathVariable("id") String id){

        return ResponseEntity.ok().body(accountService.updateAccount(model));

    }
    @DeleteMapping("/delete-user")
    public ResponseEntity<ResponseDTO> deleteUser(@RequestParam(value = "id") String id) {
      return ResponseEntity.ok().body(accountService.deleteAccount(id));
  }
}
