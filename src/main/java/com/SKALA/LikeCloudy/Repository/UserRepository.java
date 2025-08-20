package com.SKALA.LikeCloudy.Repository;

import java.util.Collection;
import java.util.Optional;

import com.SKALA.LikeCloudy.Domain.User;

public interface UserRepository {
    // 조회
    Collection<User> findAll();

    Optional<User> findById(Long id);

    // 생성 (id 없으면 자동 생성)
    User create(User user);

    // 갱신 (존재하지 않으면 예외)
    User update(Long id, User user);

    // 삭제 (존재하지 않으면 무시 또는 예외 - 여기선 무시)
    void delete(Long id);
}
