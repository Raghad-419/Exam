package com.example.exam.Service;

import com.example.exam.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    ArrayList<User> users =new ArrayList<>();

    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(String id,User user){
        for(User u: users){
            if(u.getID().equals(id)){
                users.set(users.indexOf(u),user);
                        return true;
            }
        }
        return false;
    }


    public boolean deleteUser(String id){
        for(User u: users){
            if(u.getID().equals(id)){
                users.remove(users.indexOf(u));
                return true;
            }
        }
        return false;
    }


    public ArrayList<User> balance_or_above(double balance){
        ArrayList<User> usersAboveBalance =new ArrayList<>();
        for(User u:users){
            if(u.getBalance()>=balance){
                usersAboveBalance.add(u);
            }
        }
        if(usersAboveBalance.isEmpty()){
            return null;
        }
        return usersAboveBalance;
    }


    public ArrayList<User> age_or_above(int age){
        ArrayList<User> usersAboveAge =new ArrayList<>();
        for(User u: users){
            if(u.getAge()>=age){
                usersAboveAge.add(u);

            }
        }
        if(usersAboveAge.isEmpty()){
            return null;

        }
    return usersAboveAge;

    }


}
