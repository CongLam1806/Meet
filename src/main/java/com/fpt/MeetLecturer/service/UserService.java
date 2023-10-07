package com.fpt.MeetLecturer.service;


import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.mapper.MapUser;

import com.fpt.MeetLecturer.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired(required = false)
    private UserRepository userRepository;

    @Autowired(required = false)
    private MapUser mapUser;

    private ModelMapper modelMapper = new ModelMapper();

    public ResponseDTO getUser()
    {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "FOUND ALL USERS", mapUser.convertListToUserDTO(userRepository.findAll()));
        return responseDTO;
    }

    public ResponseDTO getUserById(int id)
    {
        User user = userRepository.findById(id).orElseThrow();
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "FOUND USER", mapUser.convertUserToUserDTO(user));
        return responseDTO;
    }

    public ResponseDTO createUser(UserDTO newUser){
        User user = new User();
        modelMapper.map(newUser, user);
        userRepository.save(user);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "CREATE USER SUCCESSFULLY", mapUser.convertUserToUserDTO(user));
        return responseDTO;
    }
    public ResponseDTO updateUser(UserDTO newUser) {
        User user;
        user = userRepository.findById(newUser.getId()).orElseThrow();
        modelMapper.map(newUser, user);
        userRepository.save(user);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "UPDATE USER SUCCESSFULLY", mapUser.convertUserToUserDTO(user));
        return responseDTO;
    }

    //delete user
    public boolean deleteUser(int id) {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()) {
            return false;
        } else {
            //User delUser = user1.get();
            //userRepository.delete(user1.get());
            if (!user1.get().isStatus()) {
                return false;
            }
            user1.get().setStatus(false);
            userRepository.save(user1.get());
            //mapUser.mapUserToUserDTO(delUser);
            return true;
        }
    }
}
