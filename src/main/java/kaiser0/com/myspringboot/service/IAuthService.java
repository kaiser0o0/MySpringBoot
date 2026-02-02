package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.Result;
import kaiser0.com.myspringboot.dto.request.LoginRequest;
import kaiser0.com.myspringboot.dto.request.RegisterRequest;
import kaiser0.com.myspringboot.dto.response.AuthResponse;

public interface IAuthService {
    Result<AuthResponse> register(RegisterRequest request);
    Result<AuthResponse> login(LoginRequest request);
}