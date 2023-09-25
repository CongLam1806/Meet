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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapUser mapUser;

    @Autowired
    private ModelMapper modelMapper;

    public List<User> get(){
        return userRepository.findAll();
    }

//    public UserDTO save(UserDTO userDTO){
//
//        User user;
//        if(userDTO.getId() != 0){
//            Optional<User> oldUser = userRepository.findById(userDTO.getId());
//
//        } else {
//            user = new User();
//        }
//        modelMapper.map(userDTO, user);
//
//        user = userRepository.save(user);
//        return modelMapper(user, userDTO);
//    }
}
