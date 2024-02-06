package com.sparta.todo.service;

import com.sparta.todo.dto.SigninRequestDto;
import com.sparta.todo.dto.SignupRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor //final, @NotNull만 생성자로 만들어줌 = 생성자 주입에 사용
//이거 안쓰면 @Autowired하고(생략가능) 생성자 만들어줘야함
//필드로 있는 생성자 모두 만들어줌 = @AllArgsConstructor
//파라미터X 기본생성자는 @NoArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;



    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
       String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 이름 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        // Optional에 있는 메소드임 isPresent() DB에 존재하는지 확인해줌
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 이름이 존재합니다.");
        }

        // 사용자 등록
        User user = new User(username, password);
        userRepository.save(user);
    }

}
