package com.example.demo.Rest;

import com.example.aouth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserOauthController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/user")
    public ResponseEntity<?> user(Principal principal) throws IllegalAccessException {
        if (principal instanceof AbstractAuthenticationToken) {
            return ResponseEntity.ok(userService.getUser((AbstractAuthenticationToken) principal));
        }
        else
            throw new IllegalAccessException();
    }
}
