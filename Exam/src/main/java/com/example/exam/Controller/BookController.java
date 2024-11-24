package com.example.exam.Controller;

import com.example.exam.ApiResponse.ApiResponse;
import com.example.exam.Model.Book;
import com.example.exam.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    BookService bookService =new BookService();

    @GetMapping("/get")
    public ResponseEntity getBooks(){
        return ResponseEntity.status(200).body(bookService.getBooks());
    }

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid Book book, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        bookService.addBook(book);
        return ResponseEntity.status(200).body(new ApiResponse("Book Added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable String id, @RequestBody @Valid Book book ,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(bookService.updateBook(id,book)){
            return ResponseEntity.status(200).body(new ApiResponse("Book updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable String id){
        if(bookService.deleteBook(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Book deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));

    }


    @GetMapping("/getByName/{name}")
public ResponseEntity getByName(@PathVariable String name){
        if(bookService.getByName(name)==null){
            return ResponseEntity.status(400).body(new ApiResponse("Name not found"));
        }

        return ResponseEntity.status(200).body(bookService.getByName(name));
}


@GetMapping("/getByCategory/{category}")
public ResponseEntity getByCategory(@PathVariable String category){
        if(bookService.getByCategory(category)==null){
            return ResponseEntity.status(400).body(new ApiResponse("No books in this category"));
        }
        return ResponseEntity.status(200).body(bookService.getByCategory(category));
}

@GetMapping("/pages_or_above/{pageNum}")
public ResponseEntity pages_or_above(@PathVariable int pageNum){
        if(bookService.pages_or_above(pageNum)==null){
            return ResponseEntity.status(400).body(new ApiResponse("No books have this number of pages or above"));
        }
        return ResponseEntity.status(200).body(bookService.pages_or_above(pageNum));
}

@PutMapping("/changeStatus/{bookID}/{userID}")
public ResponseEntity changeStatus(@PathVariable String bookID, @PathVariable String userID){
        String result =bookService.changeStatus(bookID,userID);
        if(result.equals("true")){
            return ResponseEntity.status(200).body(new ApiResponse("Book Status change"));
        }
        if(result.equals("Book Not found")){
            return ResponseEntity.status(400).body(new ApiResponse("Book Not found"));
        }
        if(result.equals("Not librarian")){
            return ResponseEntity.status(400).body(new ApiResponse("Not librarian"));

        }
        return ResponseEntity.status(400).body(new ApiResponse(bookService.changeStatus(bookID,userID)));
}


}
