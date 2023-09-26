package com.fpt.MeetLecturer.Service;

import com.fpt.MeetLecturer.BusinessModel.UserDTO;
import com.fpt.MeetLecturer.EntityModel.Lecturer;
import com.fpt.MeetLecturer.EntityModel.User;
import com.fpt.MeetLecturer.Mapper.MapUser;
import com.fpt.MeetLecturer.Repository.UserRepository;
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



    public List<User> get(){
        return userRepository.findAll();
    }

    public UserDTO updateUser(UserDTO newUser) {
        Optional<User> optionalUser = userRepository.findById(newUser.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(newUser.getName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setRole(newUser.getRole());
            existingUser.setPassword(newUser.getPassword());

            userRepository.save(existingUser);
            return mapUser.convertUserToUserDTO(existingUser);
        } else {

            User user = mapUser.convertUserDTOToUser(newUser);
            return mapUser.convertUserToUserDTO(user);
        }
    }
}
