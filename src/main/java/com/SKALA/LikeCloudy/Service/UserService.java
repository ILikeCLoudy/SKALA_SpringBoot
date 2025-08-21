package com.SKALA.LikeCloudy.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SKALA.LikeCloudy.Domain.User;
import com.SKALA.LikeCloudy.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(Optional<String> name) {
        if (name.isPresent() && !name.get().isBlank()) {
            return userRepository.findByNameIgnoreCase(name.get().trim());
        }
        return userRepository.findAll();
    }

    @Transactional
    public User create(User user) {
        if (user.getEmail()!=null && userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    @Transactional
    public Optional<User> update(long id, User updated) {
        return userRepository.findById(id).map(existing -> {
            if (updated.getName()!=null)  existing.setName(updated.getName());
            if (updated.getEmail()!=null) existing.setEmail(updated.getEmail());
            return userRepository.save(existing);
        });
    }

    @Transactional
    public boolean delete(long id) {
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    // ❌ region 관련 메서드들 제거
}
