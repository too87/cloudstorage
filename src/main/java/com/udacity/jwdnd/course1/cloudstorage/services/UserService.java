package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper mapper;

    private final HashService hashService;

    public UserService(UserMapper mapper, HashService hashService) {
        this.mapper = mapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return mapper.getUserByUserName(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return mapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public User getUser(String username) {
        return mapper.getUserByUserName(username);
    }

    public Integer getUserIdByUsername(String username) {
        User user = getUser(username);
        return user == null ? null : user.getUserId();
    }
}
