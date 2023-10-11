package com.fpt.MeetLecturer.service;


import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.AccountDTO;
import com.fpt.MeetLecturer.entity.Account;
import com.fpt.MeetLecturer.mapper.MapAccount;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import com.fpt.MeetLecturer.repository.StudentRepository;
import com.fpt.MeetLecturer.util.Utility;

import com.fpt.MeetLecturer.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired(required = false)
    private AccountRepository accountRepository;

    @Autowired(required = false)
    private StudentRepository studentRepository;
    @Autowired(required = false)
    private LecturerRepository lecturerRepository;

    @Autowired(required = false)
    private MapAccount mapAccount;
    @Autowired
    private Utility utility;
    private ModelMapper modelMapper = new ModelMapper();

    public ResponseDTO getAccount()
    {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "FOUND ALL ACCOUNTS", mapAccount.convertListToAccountDTO(accountRepository.findAll()));
        return responseDTO;
    }
//    public UserDTO getById(int id){
//        return mapUser.toUserDTO(userRepository.findById(id));
//    }

    public ResponseDTO getAccountById(String id)
    {
        Account account = accountRepository.findById(id).orElseThrow();
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "FOUND ACCOUNT", mapAccount.convertAccountToAccountDTO(account));
        return responseDTO;
    }

//    public ResponseDTO createUser(UserDTO newUser){
//        User user = new User();
//        modelMapper.map(newUser, user);
//        userRepository.save(user);
//        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "CREATE USER SUCCESSFULLY", mapUser.convertUserToUserDTO(user));
//        return responseDTO;
//    }
    public ResponseDTO updateAccount(AccountDTO newAccount) {
        Account account;
        account = accountRepository.findById(newAccount.getId()).orElseThrow();
        modelMapper.map(newAccount, account);
        accountRepository.save(account);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "UPDATE ACCOUNT SUCCESSFULLY", mapAccount.convertAccountToAccountDTO(account));
        return responseDTO;
    }

    //delete user
//    public ResponseDTO deleteUser(int id) {
//        Optional<User> user1 = userRepository.findById(id);
//        if (user1.isEmpty()) {
//            return new ResponseDTO(HttpStatus.NOT_FOUND, "Id not exist", "");
//        } else {
//            //User delUser = user1.get();
//            //userRepository.delete(user1.get());
//            if (!user1.get().isStatus()) {
//                return new  ResponseDTO(HttpStatus.NOT_FOUND, "User already removed!!", "");
//            }
//            user1.get().setStatus(false);
//            userRepository.save(user1.get());
//            //mapUser.mapUserToUserDTO(delUser);
//            return new ResponseDTO(HttpStatus.OK, "Delete successfully!", "");
//        }
//    }
//    private boolean  checkUserRole(String email){
//        return utility.isStudent(email);
//    }
//    public ResponseDTO createUser2(UserDTO newUser){
//        User user = new User();
//        boolean checkRole = checkUserRole(newUser.getEmail());
//        if(checkRole){
//            newUser.setRole(2);
//        }else {
//            newUser.setRole(1);
//        }
//        recordUser(newUser); // map user based on their role
//        modelMapper.map(newUser, user);
//        userRepository.save(user);
//        return new ResponseDTO(HttpStatus.OK, "CREATE USER SUCCESSFULLY", mapUser.convertUserToUserDTO(user));
//    }
//    private void recordUser(UserDTO userDTO){
//        Lecturer lecturer = new Lecturer();
//        Student student = new Student();
//        if(userDTO.getRole() == 1){
//            modelMapper.map(userDTO, lecturer);
//            lecturerRepository.save(lecturer);
//        } else if(userDTO.getRole() == 2){
//            modelMapper.map(userDTO, student);
//            studentRepository.save(student);
//        }
//    }
}
