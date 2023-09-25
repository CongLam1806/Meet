package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.BusinessModel.UserDTO;
import com.fpt.MeetLecturer.EntityModel.User;
import com.fpt.MeetLecturer.Mapper.mapUser;
import com.fpt.MeetLecturer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private mapUser mapUser;

    public List<User> get(){
        return userRepository.findAll();
    }

    public UserDTO updateUser(User user, int id){
        Optional<User> OpUser = userRepository.findById(id);
        if(OpUser.isPresent()){
            User ExUser = OpUser.get();
            ExUser.setName(user.getName());
            ExUser.setEmail(user.getEmail());
            ExUser.setPassword(user.getPassword());
            userRepository.save(ExUser);
            return mapUser.mapUserToUserDTO(ExUser);
        } else {
            user.setId(id);
            return mapUser.mapUserToUserDTO(user);
        }
    }
    public boolean deleteUser(User user, int id){
        Optional<User> user1 = userRepository.findById(id);
        if(user1.isPresent()){
            User delUser = user1.get();
            userRepository.delete(user1.get());
            mapUser.mapUserToUserDTO(delUser);
            return true;
        }
        return false;
    }
}
