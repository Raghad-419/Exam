package com.example.exam.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "Empty ID")
    private String ID ;
    @NotEmpty(message = "Empty name")
    private String name ;
    @NotNull(message = "Empty age")
    private int age;
    @NotNull(message = "Empty balance")
    @PositiveOrZero(message = "Balance should be positive or zero")
    private double balance;
    @NotEmpty(message = "Empty role")
    @Pattern(regexp = "customer|librarian",message = " role is customer OR librarian ")
    private String role;


}
