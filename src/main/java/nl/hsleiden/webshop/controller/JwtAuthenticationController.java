package nl.hsleiden.webshop.controller;

import nl.hsleiden.webshop.entity.JwtRequest;
import nl.hsleiden.webshop.entity.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import nl.hsleiden.webshop.service.implementations.JwtUserDetailsServiceImpl;
import nl.hsleiden.webshop.utility.JwtTokenUtil;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        userDetailsService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody Users user) {
        userDetailsService.saveUser(user);

        return ResponseEntity.ok(user);
    }
}
