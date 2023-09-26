package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.businessModel.UserDTO;
import com.fpt.MeetLecturer.entityModel.User;
import com.fpt.MeetLecturer.mapper.mapUser;
import com.fpt.MeetLecturer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired(required = false)
    private mapUser mapUser;
    //get all user
    public List<User> get(){
        return userRepository.findAll();
    }

    //update user
    public UserDTO updateUser(UserDTO user, int id){
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
            User ExUser = mapUser.mapUserDTOToUser(user);
            return mapUser.mapUserToUserDTO(ExUser);
        }
    }

    //delete user
    public boolean deleteUser(int id){
        Optional<User> user1 = userRepository.findById(id);
        if(user1.isEmpty()){
            return false;
        }else{
            //User delUser = user1.get();
            //userRepository.delete(user1.get());
            if(!user1.get().isStatus()){
                return false;
            }
            user1.get().setStatus(false);
            userRepository.save(user1.get());
            //mapUser.mapUserToUserDTO(delUser);
            return true;
        }

    }
}
