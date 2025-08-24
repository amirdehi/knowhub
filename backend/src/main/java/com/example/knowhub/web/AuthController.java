package com.example.knowhub.web;

import com.example.knowhub.domain.User;
import com.example.knowhub.repo.RoleRepo;
import com.example.knowhub.repo.UserRepo;
import com.example.knowhub.security.JwtService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepo users;
    private final RoleRepo roles;
    private final JwtService jwt;

    public AuthController(UserRepo users, RoleRepo roles, JwtService jwt) {
        this.users = users;
        this.roles = roles;
        this.jwt = jwt;
    }

    public record LoginReq(@NotBlank String username, @NotBlank String password) {}
    public record RegisterReq(@NotBlank String username, @NotBlank String email, @NotBlank String displayName, @NotBlank String password) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req){
        var u = users.findByUsername(req.username()).orElse(null);
        if(u==null || !BCrypt.checkpw(req.password(), u.getPasswordHash())) return ResponseEntity.status(401).body(Map.of("error","invalid"));
        var token = jwt.createAccessToken(u.getUsername());
        return ResponseEntity.ok(Map.of("accessToken", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq req){
        if(users.findByUsername(req.username()).isPresent()) return ResponseEntity.badRequest().body(Map.of("error","exists"));
        User u = new User();
        u.setUsername(req.username()); u.setEmail(req.email()); u.setDisplayName(req.displayName());
        u.setPasswordHash(BCrypt.hashpw(req.password(), BCrypt.gensalt(10)));
        u.getRoles().add(roles.findByName("TEAM_MEMBER").orElseThrow());
        users.save(u);
        return ResponseEntity.ok(Map.of("ok",true));
    }
}
