package com.example.exam.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    @NotEmpty(message = "Empty ID")
    private String ID;
    @NotEmpty(message = "Empty name")
    private String name ;
    @NotNull(message = "Empty number of pages")
    private int number_of_pages ;
    @NotNull(message = "Empty price")
    private double price;
    @NotEmpty(message = "Empty category")
    @Pattern(regexp = "novel|academic",message = "Category novel OR academic")
    private String category ;
    @NotNull(message = "Empty Availability")
    private boolean isAvailable ;

}
