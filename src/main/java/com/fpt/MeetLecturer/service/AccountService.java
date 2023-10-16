package com.fpt.MeetLecturer.service;


import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.AccountDTO;
import com.fpt.MeetLecturer.entity.Account;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Student;
import com.fpt.MeetLecturer.mapper.MapAccount;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import com.fpt.MeetLecturer.repository.StudentRepository;
import com.fpt.MeetLecturer.util.Utility;

import com.fpt.MeetLecturer.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    public ResponseDTO updateAccount(AccountDTO newAccount) {
        Account account;
        account = accountRepository.findById(newAccount.getId()).orElseThrow();
        modelMapper.map(newAccount, account);
        accountRepository.save(account);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "UPDATE ACCOUNT SUCCESSFULLY", mapAccount.convertAccountToAccountDTO(account));
        return responseDTO;
    }

//    delete user
    public ResponseDTO deleteAccount(String id) {
        Optional<Account> user1 = accountRepository.findById(id);
        if (user1.isEmpty()) {
            return new ResponseDTO(HttpStatus.NOT_FOUND, "Id not exist", "");
        } else {
            if (!user1.get().isStatus()) {
                return new  ResponseDTO(HttpStatus.NOT_FOUND, "User already removed!!", "");
            }
            user1.get().setStatus(false);
            accountRepository.save(user1.get());
            return new ResponseDTO(HttpStatus.OK, "Delete successfully!", "");
        }
    }
    private boolean  checkUserRole(String email){
        return utility.isStudent(email);
    }
    public ResponseDTO createUser2(AccountDTO newAccount){
        Account user = new Account();
        boolean checkRole = checkUserRole(newAccount.getEmail());
        if(newAccount.getRole() == -1){
            if(checkRole){
                newAccount.setRole(2);
            }else {
                newAccount.setRole(1);
            }
        }
        recordUser(newAccount); // map user based on their role
        modelMapper.map(newAccount, user);
        accountRepository.save(user);
        return new ResponseDTO(HttpStatus.OK, "CREATE USER SUCCESSFULLY", mapAccount.convertAccountToAccountDTO(user));
    }
    private void recordUser(AccountDTO accountDTO){
        Lecturer lecturer = new Lecturer();
        Student student = new Student();
        if(accountDTO.getRole() == 1){
            modelMapper.map(accountDTO, lecturer);
            lecturerRepository.save(lecturer);
        } else if(accountDTO.getRole() == 2){
            modelMapper.map(accountDTO, student);
            String code = utility.extractStudentId(student.getEmail());
            String curiculum = utility.extractCuriculum(student.getName());
            String defaultAddress = utility.extractDefaultAddress((student.getAddress()));
            student.setCode(code);
            student.setCurriculum(curiculum);
            student.setAddress(defaultAddress);
            studentRepository.save(student);
        }
    }
}
