package com.fpt.MeetLecturer.Repository;

import com.fpt.MeetLecturer.EntityModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
