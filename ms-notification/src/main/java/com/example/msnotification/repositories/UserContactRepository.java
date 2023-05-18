package com.example.msnotification.repositories;

import com.example.msnotification.entity.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactRepository extends JpaRepository<UserContact,Long> {

    UserContact findUserContactByNin(String nin);

}
