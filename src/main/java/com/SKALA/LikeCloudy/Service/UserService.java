package com.SKALA.LikeCloudy.Service;

import com.SKALA.LikeCloudy.Domain.User;
import com.SKALA.LikeCloudy.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(Optional<String> name) {
        return name.map(userRepository::findByNameIgnoreCase)
                   .orElseGet(userRepository::findAll);
    }

    public List<User> findByRegionId(Long regionId) {
        return userRepository.findByRegionId(regionId);
    }

    public List<User> findByRegionName(String regionName) {
        return userRepository.findByRegion_Name(regionName);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public boolean delete(Long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }

    public Optional<User> update(Long id, User updated) {
        return userRepository.findById(id).map(u -> {
            u.setName(updated.getName());
            u.setEmail(updated.getEmail());
            u.setRegion(updated.getRegion());
            return userRepository.save(u);
        });
    }
}
