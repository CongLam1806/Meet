package com.fpt.MeetLecturer.service;


import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Student;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.mapper.MapUser;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import com.fpt.MeetLecturer.repository.StudentRepository;
import com.fpt.MeetLecturer.util.Utility;

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
    private StudentRepository studentRepository;
    @Autowired(required = false)
    private LecturerRepository lecturerRepository;

    @Autowired(required = false)
    private MapUser mapUser;
    @Autowired
    private Utility utility;
    private ModelMapper modelMapper = new ModelMapper();

    public ResponseDTO getUser()
    {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "FOUND ALL USERS", mapUser.convertListToUserDTO(userRepository.findAll()));
        return responseDTO;
    }
//    public UserDTO getById(int id){
//        return mapUser.toUserDTO(userRepository.findById(id));
//    }

    public ResponseDTO getUserById(int id)
    {
        User user = userRepository.findById(id).orElseThrow();
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "FOUND USER", mapUser.convertUserToUserDTO(user));
        return responseDTO;
    }

//    public ResponseDTO createUser(UserDTO newUser){
//        User user = new User();
//        modelMapper.map(newUser, user);
//        userRepository.save(user);
//        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "CREATE USER SUCCESSFULLY", mapUser.convertUserToUserDTO(user));
//        return responseDTO;
//    }
    public ResponseDTO updateUser(UserDTO newUser) {
        User user;
        user = userRepository.findById(newUser.getId());
        modelMapper.map(newUser, user);
        userRepository.save(user);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "UPDATE USER SUCCESSFULLY", mapUser.convertUserToUserDTO(user));
        return responseDTO;
    }

    //delete user
    public ResponseDTO deleteUser(int id) {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()) {
            return new ResponseDTO(HttpStatus.NOT_FOUND, "Id not exist", "");
        } else {
            //User delUser = user1.get();
            //userRepository.delete(user1.get());
            if (!user1.get().isStatus()) {
                return new  ResponseDTO(HttpStatus.NOT_FOUND, "User already removed!!", "");
            }
            user1.get().setStatus(false);
            userRepository.save(user1.get());
            //mapUser.mapUserToUserDTO(delUser);
            return new ResponseDTO(HttpStatus.OK, "Delete successfully!", "");
        }
    }
    private boolean  checkUserRole(String email){
        return utility.isStudent(email);
    }
    public ResponseDTO createUser2(UserDTO newUser){
        User user = new User();
        boolean checkRole = checkUserRole(newUser.getEmail());
        if(checkRole){
            newUser.setRole(2);
        }else {
            newUser.setRole(1);
        }
        recordUser(newUser); // map user based on their role
        modelMapper.map(newUser, user);
        userRepository.save(user);
        return new ResponseDTO(HttpStatus.OK, "CREATE USER SUCCESSFULLY", mapUser.convertUserToUserDTO(user));
    }
    private void recordUser(UserDTO userDTO){
        Lecturer lecturer = new Lecturer();
        Student student = new Student();
        if(userDTO.getRole() == 1){
            modelMapper.map(userDTO, lecturer);
            lecturerRepository.save(lecturer);
        } else if(userDTO.getRole() == 2){
            modelMapper.map(userDTO, student);
            studentRepository.save(student);
        }
    }
}
