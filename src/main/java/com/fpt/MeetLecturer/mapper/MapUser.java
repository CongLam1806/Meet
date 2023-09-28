package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MapUser {
    private static final ModelMapper modelMapper = new ModelMapper();

    public  User convertUserDTOToUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public List<User> convertListToUser(List<UserDTO> usersDTO){
        List<User> list = new ArrayList<>();
        usersDTO.forEach(userDTO -> list.add(convertUserDTOToUser(userDTO)));
        return list;
    }

    public  UserDTO convertUserToUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> convertListToUserDTO(List<User> users){
        List<UserDTO> list = new ArrayList<>();
        users.forEach(user -> list.add(convertUserToUserDTO(user)));
        return list;
    }
}
