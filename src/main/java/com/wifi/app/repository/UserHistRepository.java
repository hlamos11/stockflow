package com.wifi.app.repository;



import com.wifi.app.entity.UserHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistRepository extends JpaRepository<UserHist, Integer> {

     List<UserHist> findUserHistByUsername(String username);
}
