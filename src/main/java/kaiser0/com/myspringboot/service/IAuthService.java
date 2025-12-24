package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.request.LoginRequest;
import kaiser0.com.myspringboot.dto.response.AuthResponse;
import kaiser0.com.myspringboot.entity.User;

public interface IAuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(User user);
}