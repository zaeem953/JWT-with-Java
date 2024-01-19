package com.sms.jwt.service;

import com.sms.jwt.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store=new ArrayList<>();

    public UserService(){
        store.add(new User(UUID.randomUUID().toString(),"Zaeem","Zaeem@email.com"));
        store.add(new User(UUID.randomUUID().toString(),"Waseem","Waseem@email.com"));
        store.add(new User(UUID.randomUUID().toString(),"Saad","Saad@email.com"));
    }

    public List<User> getUsers(){
        return this.store;
    }
}
