package com.fpt.MeetLecturer.service;


import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.mapper.MapUser;

import com.fpt.MeetLecturer.repository.UserRepository;
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



    public List<UserDTO> get()
    {

        return mapUser.convertListToUserDTO(userRepository.findAll());
    }

    public void updateUser(UserDTO newUser) {
        Optional<User> optionalUser = userRepository.findById(newUser.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUserName(newUser.getName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setRole(newUser.getRole());
            existingUser.setPassword(newUser.getPassword());

            userRepository.save(existingUser);
            mapUser.convertUserToUserDTO(existingUser);
        } else {

            User user = mapUser.convertUserDTOToUser(newUser);
            userRepository.save(user);
        }
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
