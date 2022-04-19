package nl.hsleiden.webshop.controller;

import java.util.Set;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import nl.hsleiden.webshop.entity.Role;
import nl.hsleiden.webshop.entity.User;
import nl.hsleiden.webshop.entity.payloads.LoginRequest;
import nl.hsleiden.webshop.entity.payloads.TokenResponse;
import nl.hsleiden.webshop.service.interfaces.RoleService;
import nl.hsleiden.webshop.service.interfaces.UserService;
import nl.hsleiden.webshop.entity.payloads.RegisterRequest;
import nl.hsleiden.webshop.entity.payloads.MessageResponse;
import nl.hsleiden.webshop.service.implementations.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public TokenResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = userDetailsServiceImpl.getTokenResponse(loginRequest);

        return tokenResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        } else if (userService.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(registerRequest.getUsername(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword())
        );
        Set<Role> roles = roleService.verifyRoles(registerRequest.getRole());
        user.setRoles(roles);
        userService.saveUser(user);

        return ResponseEntity.ok(new MessageResponse("User successfully registered!"));
    }

    @GetMapping("/validate/username/{username}")
    public boolean validateUsername(@PathVariable String username) {

        return userService.existsByUsername(username);
    }

    @GetMapping("validate/email/{email}")
    public boolean validateEmail(@PathVariable String email) {

        return userService.existsByEmail(email);
    }
}
