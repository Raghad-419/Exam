package com.example.exam.Controller;

import com.example.exam.ApiResponse.ApiResponse;
import com.example.exam.Model.Book;
import com.example.exam.Model.User;
import com.example.exam.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    UserService userService =new UserService();

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id,@RequestBody @Valid User user ,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
    if(userService.updateUser(id,user)){
        return ResponseEntity.status(200).body(new ApiResponse("User updated"));
    }
    return ResponseEntity.status(400).body(new ApiResponse("ID not found"));


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @GetMapping("/balance_or_above/{balance}")
public ResponseEntity balance_or_above(@PathVariable double balance){
        if(userService.balance_or_above(balance) == null){
            return ResponseEntity.status(400).body(new ApiResponse("No users have this balance or above"));
        }
        return ResponseEntity.status(200).body(userService.balance_or_above(balance));
}



@GetMapping("/age_or_above/{age}")
public ResponseEntity age_or_above(@PathVariable int age){
        if(userService.age_or_above(age)==null){
            return ResponseEntity.status(400).body(new ApiResponse("No users have this age or above"));
        }
        return ResponseEntity.status(200).body(userService.age_or_above(age));
}




}
