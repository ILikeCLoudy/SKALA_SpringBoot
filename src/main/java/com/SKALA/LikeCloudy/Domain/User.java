package com.SKALA.LikeCloudy.Domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private @Positive Long id;
    private @NotBlank String name;
    private @Email String email;
    private @Size(min = 1, max = 10) List<String> hobbies; // 취미

    @JsonCreator
    public User(@JsonProperty("id") Long id,
                @JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("hobbies") List<String> hobbies) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hobbies = hobbies;
    }
}