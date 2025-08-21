package com.SKALA.LikeCloudy.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.SKALA.LikeCloudy.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameIgnoreCase(String name);
    boolean existsByEmail(String email);
}
