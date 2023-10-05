package com.fpt.MeetLecturer.service;


import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.mapper.MapUser;

import com.fpt.MeetLecturer.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<UserDTO> get()
    {
        return mapUser.convertListToUserDTO(userRepository.findAll());
    }

    public void createUser(UserDTO newUser){
        User user = new User();
        modelMapper.map(newUser, user);
        userRepository.save(user);
    }
    public void updateUser(UserDTO newUser) {
            User user;
            user = userRepository.findById(newUser.getId()).orElseThrow();

            modelMapper.map(newUser, user);
            userRepository.save(user);
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
