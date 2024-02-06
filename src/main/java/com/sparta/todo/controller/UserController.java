package com.sparta.todo.controller;

import com.sparta.todo.dto.SigninRequestDto;
import com.sparta.todo.dto.SignupRequestDto;
import com.sparta.todo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/users")
//회원가입, 로그인
//로그인 시 성공 메시지 출력 X 구현 필요
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //validation적용
   @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        try{
            userService.signup(requestDto);
        }catch (IllegalArgumentException e){
            //중복된 사용자
            return ResponseEntity.badRequest().body("회원가입 실패! 중복된 사용자입니다. 상태 코드 : "+ResponseEntity.badRequest().build().getStatusCode());
        }
       List<FieldError> fieldErrors = bindingResult.getFieldErrors();
       if(!fieldErrors.isEmpty()) {
           //이름, 비밀번호 형식에 맞지 않게 입력
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body("회원가입 실패! 형식에 맞지 않습니다. 상태 코드 : "+ResponseEntity.badRequest().build().getStatusCode());
       }else{
            return ResponseEntity.ok().body("회원가입 성공! 상태 코드 : "+ResponseEntity.ok().build().getStatusCode());
        }
    }

}
