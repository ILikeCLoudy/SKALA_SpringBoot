package com.SKALA.LikeCloudy.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FileUserRepositoryConfig {
    @Bean
    public Path dataFile() {
        // 원하는 파일 경로로 수정하세요
        return Paths.get("data/users.json");
    }
}