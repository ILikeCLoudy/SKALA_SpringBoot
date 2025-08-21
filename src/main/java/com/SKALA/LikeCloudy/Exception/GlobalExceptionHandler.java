package com.SKALA.LikeCloudy.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.SKALA.LikeCloudy")
@Slf4j
public class GlobalExceptionHandler {

    // 전역적으로 400 Bad Request 예외 처리
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body("Global 400 Error: " + ex.getMessage());
    }

    // 전역적으로 500 Internal Server Error 예외 처리
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<String> handleInternalServerException(RuntimeException ex) {
        return ResponseEntity.internalServerError().body("Global 500 Error: " + ex.getMessage());
    }

    // 기타 모든 예외 처리 (예상하지 못한 모든 예외 - 추후 추가 가능)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Unhandled error", ex); // ← 스택 찍기
        return ResponseEntity.internalServerError()
                .body("Global Error: 알 수 없는 오류가 발생하였습니다. 관리자에게 문의하세요.");
    }
}
