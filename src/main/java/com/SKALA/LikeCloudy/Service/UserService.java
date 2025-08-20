package com.SKALA.LikeCloudy.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.SKALA.LikeCloudy.Domain.User;
import com.SKALA.LikeCloudy.Repository.UserRepository;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(Optional<String> name) {
        List<User> users = userRepository.findAll().stream().toList();
        if (name.isPresent()) {
            String searchName = name.get();
            return users.stream()
                    .filter(user -> user.getName() != null && user.getName().equalsIgnoreCase(searchName))
                    .collect(Collectors.toList());
        } else {
            return users;
        }
    }

    public User create(User user) {
        return userRepository.create(user);
    }

    public Optional<User> update(long id, User updated) {
        try {
            return Optional.of(userRepository.update(id, updated));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean delete(long id) {
        try {
            userRepository.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
}