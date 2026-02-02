package kaiser0.com.myspringboot.controller;

import kaiser0.com.myspringboot.dto.Result;
import kaiser0.com.myspringboot.dto.request.LoginRequest;
import kaiser0.com.myspringboot.dto.request.RegisterRequest;
import kaiser0.com.myspringboot.dto.response.AuthResponse;
import kaiser0.com.myspringboot.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Result<AuthResponse>> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Result<AuthResponse>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}