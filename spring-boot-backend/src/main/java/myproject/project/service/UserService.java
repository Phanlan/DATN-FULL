package myproject.project.service;

import lombok.AllArgsConstructor;
import myproject.project.entity.*;
import myproject.project.model.request.LoginRequest;
import myproject.project.model.request.RegisterRequest;
import myproject.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        boolean emailExits = userRepository.existsByEmail(registerRequest.getEmail());
        if (emailExits) {
            return new ResponseEntity<>("Email have existed!", HttpStatus.BAD_REQUEST);
        }

//        Role userRole =roleRepository.findByName(RoleType.USER)
//                .orElseThrow(
//                        () -> new RuntimeException("Error: Role is not found.")
//                );

        User user = User.builder()
                .email(registerRequest.getEmail())
                .fullName(registerRequest.getFullname())
                .phoneNumber(registerRequest.getPhone())
                .password(registerRequest.getPassword())
                .birthday(registerRequest.getBirthday())
                .address(registerRequest.getAddress())
//                .role(userRole)
                .status(Status.ACTIVE)
                .build();
        userRepository.save(user);
        return new ResponseEntity<>("Register your account success!", HttpStatus.OK);
    }

    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new Exception("User not exist");
        return user;
    }
    public ResponseEntity<?> login(LoginRequest loginRequest) throws Exception {
        User user = getUserByEmail(loginRequest.getEmail());
        System.out.println(user);
        if (user.getStatus().equals(Status.NON_ACTIVE)) {
            return new ResponseEntity<>("your account is not active", HttpStatus.BAD_REQUEST);
        }
            return new ResponseEntity<>("Login your account success!", HttpStatus.OK);
        }
    }




