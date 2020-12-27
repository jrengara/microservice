package com.microservice.restful.user;

import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Component
public class UserDaoService {

    private static  List<User> userList = new ArrayList<>();
    private static int usersCount = 3;

    static {
        userList.add(new User(1,"jack",new Date()));
        userList.add(new User(2,"sparrow",new Date()));
        userList.add(new User(3,"ship",new Date()));
    }

    public List<User> findAllUser(){
        return  userList;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        userList.add(user);
        return user;
    }

    public User getUser(Integer id){
        List<User> list = userList.stream().filter(user -> user.getId()==id).collect(Collectors.toList());
        if(list.size()==0){
            return null;
        }
        return list.get(0);
    }

    public User deleteUser(Integer id){
        Iterator<User> iterator = userList.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId()==id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
