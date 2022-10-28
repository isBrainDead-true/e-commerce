package com.ecommer.backend.controller;

import com.ecommer.backend.dto.ResponseDTO;
import com.ecommer.backend.dto.UserDTO;
import com.ecommer.backend.session.InMemorySessionRegistry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class AuthController {

    public AuthenticationManager manager;
    public InMemorySessionRegistry sessionRegistry;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user){
        manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        final String sessionId = sessionRegistry.registerSession(user.getUsername());
        ResponseDTO response = new ResponseDTO();
        response.setSessionID(sessionId);
        return ResponseEntity.ok().body(response);
    }


}
