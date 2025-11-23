package practical.employee.register.service.impl;

import org.springframework.stereotype.Service;
import practical.employee.register.model.User;
import practical.employee.register.repository.UserRepository;
import practical.employee.register.request.LoginRequest;
import practical.employee.register.service.UserService;
import practical.employee.register.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil)
    {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(LoginRequest request)
    {
        String user = request.getUsername();
        return jwtUtil.generateToken(user);
    }
}
