package com.example.board.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private final Long id;

    private final String password;

    private final Integer age;

    public SignUpResponseDto(Long id, String password, Integer age) {
        this.id = id;
        this.password = password;
        this.age = age;
    }
}
