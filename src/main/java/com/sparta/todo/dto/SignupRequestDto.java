package com.sparta.todo.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Size(min=4,max=10,message = "4~10자리로 입력하십시오.")
    @Pattern(regexp = "^[a-z0-9]+$",message = "영어 소문자와 숫자로 입력해야 합니다.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]+$",message = "영어 대소문자와 숫자로 입력해야 합니다.")
    @Size(min=8,max=15,message="8~15자리로 입력하십시오.")
    private String password;
}
