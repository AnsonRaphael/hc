package com.nanos.irctc.repository.user;

import com.nanos.irctc.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM user u " +
            "WHERE u.email = ?1"
    )
    Boolean selectExistsEmail(String email);
}
