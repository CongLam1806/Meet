package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.businessModel.UserDTO;
import com.fpt.MeetLecturer.entityModel.User;
import org.modelmapper.ModelMapper;

public class mapUser {
    public static final ModelMapper modelMapper = new ModelMapper();
    public UserDTO mapUserToUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
    public User mapUserDTOToUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

}
