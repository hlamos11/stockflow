package com.wifi.app.service;




import com.wifi.app.entity.User;
import com.wifi.app.objects.UserDTO;
import com.wifi.app.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private  final BCryptPasswordEncoder passwordEncoder;
    public static final int MAX_FAILED_ATTEMPTS = 3;
    //private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours
    private static final long LOCK_TIME_DURATION = 30 * 60 * 1000; // 30 minutes
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public List<User> getUsertList() {
        return userRepository.findAll();
    }


    @Transactional
    public Optional<User> findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }


    public User getByUsername(String username){
        return userRepository.getByUsername(username);
    }

    public boolean userExists(String username){
        return  findUserByUsername(username).isPresent();
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    public User register(UserDTO userDTO){
        //encryptar contraeña
        String password = passwordEncoder.encode(userDTO.getPassword());

        userDTO.setPassword(password);
        userDTO.setFail_attempt(0);

        User user = new User();
        //Habilitar el Usuario
        user.setEnabled(true);
        //mapear userDTO a usuario
        modelMapper.map(userDTO, user);
        return save(user);
    }

    public User changepassword(User user ){

        return save(user);

    }

    public void increaseFailedAttempts(User user) {
        int newFailAttempts = user.getFail_attempt() + 1;
        userRepository.updateFailAttempt(newFailAttempts, user.getUsername());
    }

    public void resetFailLockTime(String username) {
        userRepository.updateFailAttempt(0, username);
    }

    public void lock(User user) {

        Timestamp timestamp = new Timestamp(new Date().getTime());
        user.setEnabled(false);
        user.setLock_time(timestamp);
        userRepository.save(user);
    }

    public boolean unlockWhenTimeExpired(User user) {

        long lockTimeInMillis = user.getLock_time().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setEnabled(true);
            user.setLock_time(null);
            user.setFail_attempt(0);

            userRepository.save(user);

            return true;
        }

        return false;
    }


}
