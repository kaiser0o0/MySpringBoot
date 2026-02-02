package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.Result;
import kaiser0.com.myspringboot.dto.request.LoginRequest;
import kaiser0.com.myspringboot.dto.request.RegisterRequest;
import kaiser0.com.myspringboot.dto.response.AuthResponse;
import kaiser0.com.myspringboot.entity.Role;
import kaiser0.com.myspringboot.entity.User;
import kaiser0.com.myspringboot.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public Result<AuthResponse> register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Bu kullanıcı adı zaten alınmış.");
        }

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return Result.success(AuthResponse.builder().token(jwtToken).build(), "Kayıt başarıyla tamamlandı.");
    }

    @Override
    public Result<AuthResponse> login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        var jwtToken = jwtService.generateToken(user);
        return Result.success(AuthResponse.builder().token(jwtToken).build(), "Giriş başarılı.");
    }
}