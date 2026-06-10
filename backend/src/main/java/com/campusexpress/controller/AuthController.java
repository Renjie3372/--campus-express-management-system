package com.campusexpress.controller;

import com.campusexpress.common.Result;
import com.campusexpress.dto.LoginRequest;
import com.campusexpress.entity.User;
import com.campusexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    // mock token storage for demo purposes
    private static Map<String, User> tokenStore = new HashMap<>();

    @PostMapping("/auth/login")
    public Result<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.getAccount(), loginRequest.getPassword());
        if (user == null) {
            return Result.error(401, "账号或密码错误");
        }
        String token = "mock-token-" + user.getAccount();
        tokenStore.put(token, user);

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user);
        return Result.success(data);
    }

    @GetMapping("/auth/me")
    public Result<User> me(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        User user = tokenStore.get(token);
        if (user == null) {
            return Result.error(401, "未登录或登录已过期");
        }
        return Result.success(user);
    }

    @GetMapping("/users")
    public Result<List<User>> getUsers() {
        return Result.success(userService.list());
    }
}
