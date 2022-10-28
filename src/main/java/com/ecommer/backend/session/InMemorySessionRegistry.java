package com.ecommer.backend.session;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@Component
public class InMemorySessionRegistry  {

    private static final HashMap<String, String> SESSIONS = new HashMap<>();

    public String registerSession(String username){
        if (username == null) System.out.println("Usuario deve ser provido");
        String sessionid = generateSessionId();
        SESSIONS.put(sessionid, username);
        return sessionid;
    }

    public String getUsernameForSession(String sessionId){
        return SESSIONS.get(sessionId);
    }

    public String generateSessionId(){
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }
}
