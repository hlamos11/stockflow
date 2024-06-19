package com.wifi.app.repository;

import com.wifi.app.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findUserByUsername(String username);

    public  User findUserById(Integer id);

    @Query("UPDATE User u SET u.fail_attempt = ?1 WHERE u.username = ?2")
    @Modifying
    public void updateFailAttempt(int failAttempt, String username);

    @Query("UPDATE User u SET u.fail_attempt  = ?1, u.lock_time= null WHERE u.username = ?2")
    @Modifying
    public void updateFailLockTime(int failAttempt, String username);

    public User getByUsername(@Param("username") String username);

}
