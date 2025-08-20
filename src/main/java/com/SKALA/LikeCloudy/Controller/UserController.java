package com.SKALA.LikeCloudy.Controller;

import java.util.List;
import java.util.Optional;

import com.SKALA.LikeCloudy.Domain.User;
import com.SKALA.LikeCloudy.Exception.BadRequestException;
import com.SKALA.LikeCloudy.Exception.InternalServerException;
import com.SKALA.LikeCloudy.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 모든 사용자 조회 및 특정 사용자 이름으로 필터링
    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam Optional<String> name) {
        log.debug(name.toString());
        return userService.findAll(name);
    }

    // GET: 특정 사용자 가져오기
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: 사용자 추가
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User created = userService.create(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // DELETE: 사용자 삭제
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        boolean deleted = userService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // PUT: 사용자 정보 수정
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.update(id, updatedUser)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/400")
    public void throwBadRequest() {
        throw new BadRequestException("ㅇㄴㄹㅇㄴ잘못된 요청입니다.");
    }

    // 500 예외 발생
    @GetMapping("/500")
    public void throwInternalServerError() {
        throw new InternalServerException("ㄴㅇㄹㅇㄴㄹ서버 내부 오류가 발생했습니다.");
    }

    // 전역 예외 처리기로 전달되는 예외 발생 (ControllerAdvice에서 처리)
    @GetMapping("/global")
    public void throwGlobalException() {
        throw new RuntimeException("ㄴㅇㄹㅇㄴㄹ이 예외는 전역 예외 처리기로 전달됩니다.");
    }

    // 컨트롤러 내부에서 400 Bad Request 예외 처리
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        return ResponseEntity.badRequest().body("내 에러 : " + ex.getMessage());
    }

    // 컨트롤러 내부에서 500 Internal Server Error 예외 처리
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<String> handleInternalServerError(InternalServerException ex) {
        return ResponseEntity.status(500).body("내 에러 : " + ex.getMessage());
    }
}