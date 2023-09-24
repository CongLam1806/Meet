package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.model.User;
import com.fpt.MeetLecturer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> get(){
        return userRepository.findAll();
    }
}
