package com.fpt.MeetLecturer.Mapper;

import com.fpt.MeetLecturer.BusinessModel.UserDTO;
import com.fpt.MeetLecturer.EntityModel.User;
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
