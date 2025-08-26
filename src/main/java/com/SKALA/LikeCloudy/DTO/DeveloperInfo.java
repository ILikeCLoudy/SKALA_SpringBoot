package com.SKALA.LikeCloudy.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DeveloperInfo {
    private Owner owner;
    private Team team;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Owner {
        private String name;
        private String role;
        private String level;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class Team {
        private String position;
        private String detail;
    }
}
