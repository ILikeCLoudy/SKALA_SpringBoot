package com.SKALA.LikeCloudy.Repository;

import com.SKALA.LikeCloudy.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRegionId(Long regionId);          // FK로 조회
    List<User> findByRegion_Name(String regionName);   // region.name 경로 탐색
    List<User> findByNameIgnoreCase(String name);      // /users?name= 처리용 (이미 있으면 생략)
}
