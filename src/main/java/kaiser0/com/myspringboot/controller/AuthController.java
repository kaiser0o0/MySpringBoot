package kaiser0.com.myspringboot.controller;

import kaiser0.com.myspringboot.dto.request.LoginRequest;
import kaiser0.com.myspringboot.entity.User;
import kaiser0.com.myspringboot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }
}