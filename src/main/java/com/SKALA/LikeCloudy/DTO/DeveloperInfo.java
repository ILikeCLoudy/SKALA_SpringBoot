package com.SKALA.LikeCloudy.DTO;

public record DeveloperInfo(Owner owner, Team team) {

    public static record Owner(
            String name,
            String role,
            String level) {
    }

    public static record Team(
            String position,
            String detail) {
    }
}
