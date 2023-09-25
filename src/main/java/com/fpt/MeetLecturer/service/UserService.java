package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.BusinessModel.UserDTO;
import com.fpt.MeetLecturer.EntityModel.User;
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

    public UserDTO save(UserDTO userDTO){
        User user = new User();
        if(userDTO.getId() != 0){
            User olfUser = userRepository.find(userDTO.getId());
            user =
        } else {

        }



    }
}
