package com.fpt.MeetLecturer.repository;


import com.fpt.MeetLecturer.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    Optional<Account> findById(String Id);

    Account findByName(String name);

}
