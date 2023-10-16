package com.fpt.MeetLecturer.mapper;


import com.fpt.MeetLecturer.business.AccountDTO;
import com.fpt.MeetLecturer.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapAccount {
    private static final ModelMapper modelMapper = new ModelMapper();

//    static {
//        //Define the mapping configuration for Slot to SlotDTO
//        TypeMap<Account, AccountDTO> slotToDTOTypeMap = modelMapper.createTypeMap(Account.class, AccountDTO.class)
//                //.addMapping(src -> src.get, SlotDTO::);
//
//    }

    public Account convertAccountDTOToAccount(AccountDTO accountDTO){
        return modelMapper.map(accountDTO, Account.class);
    }

    public List<Account> convertListToAccount(List<AccountDTO> accountsDTO){
        List<Account> list = new ArrayList<>();
        accountsDTO.forEach(accountDTO -> list.add(convertAccountDTOToAccount(accountDTO)));
        return list;
    }

    public  AccountDTO convertAccountToAccountDTO(Account account){
        return modelMapper.map(account, AccountDTO.class);
    }
    public AccountDTO toAccountDTO(Account account){return modelMapper.map(account, AccountDTO.class);}
    public List<AccountDTO> convertListToAccountDTO(List<Account> accounts){
        List<AccountDTO> list = new ArrayList<>();
        accounts.forEach(account -> list.add(convertAccountToAccountDTO(account)));
        return list;
    }
}
