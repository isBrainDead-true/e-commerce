package com.ecommer.backend.repository;

import com.ecommer.backend.profile.CurrentUserApp;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class UserInMemoryRepository {

    private static final HashMap<String, CurrentUserApp> REGISTERED_USERS = new HashMap<>(2);

    public CurrentUserApp loadUserByUsername(String username){
        System.out.println(REGISTERED_USERS);
        System.out.println("Username: " + username);
        System.out.println("Objeto: "+ REGISTERED_USERS.get("user1"));
        return REGISTERED_USERS.get("user1");
    }

    @PostConstruct
    public void setup(){
        REGISTERED_USERS.put("user1", buildCurrentUser("admin", "$2a$12$AyzebMKFEKVChurFkHjY6.XSIsTxQON5iyU.JQryuk52V9G51Eg4i"));
        REGISTERED_USERS.put("user2", buildCurrentUser("customer", "$2a$12$AyzebMKFEKVChurFkHjY6.XSIsTxQON5iyU.JQryuk52V9G51Eg4i"));
    }

    private CurrentUserApp buildCurrentUser(String username, String password) {
        CurrentUserApp currentUserApp = new CurrentUserApp();
        currentUserApp.setPassword(password);
        currentUserApp.setUsername(username);
        return currentUserApp;
    }

}
