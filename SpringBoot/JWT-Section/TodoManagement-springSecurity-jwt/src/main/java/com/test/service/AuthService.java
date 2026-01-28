package com.test.service;

import com.test.dto.LoginDTO;
import com.test.dto.RegisterDTO;

public interface AuthService {
    public String register(RegisterDTO registerDTO);

    public String login(LoginDTO loginDTO);
}
