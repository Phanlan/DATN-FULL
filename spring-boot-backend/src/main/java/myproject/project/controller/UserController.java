package myproject.project.controller;

import lombok.AllArgsConstructor;
import myproject.project.model.request.LoginRequest;
import myproject.project.model.request.RegisterRequest;
import myproject.project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("public-api/v1.0.0/user")
@AllArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return userService.login(loginRequest);
    }

}
