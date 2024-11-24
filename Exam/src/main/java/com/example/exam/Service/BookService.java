package com.example.exam.Service;

import com.example.exam.Model.Book;
import com.example.exam.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    ArrayList<Book> books =new ArrayList();

    public ArrayList<Book> getBooks(){
        return books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public boolean updateBook(String id ,Book book){
        for(Book b: books){
            if(b.getID().equals(id)){
                books.set(books.indexOf(b),book);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(String id){
        for(Book b: books){
            if(b.getID().equals(id)){
            books.remove(books.indexOf(b));
            return true;
            }
        }
        return false;
    }

    public Book getByName(String name){
        for (Book b: books){
            if (b.getName().equals(name)){
                return b;
            }
        }
        return null;
    }

    public ArrayList<Book> getByCategory(String catgory){
        ArrayList<Book> booksInCategory=new ArrayList<>();
        for(Book b: books){
            if(b.getCategory().equals(catgory)){
                booksInCategory.add(b);
            }
        }
        if(booksInCategory.isEmpty()){
            return null;
        }
        return booksInCategory;
    }


    public ArrayList<Book> pages_or_above(int pageNum){
        ArrayList<Book> bookArrayList=new ArrayList();
        for (Book b: books){
            if(b.getNumber_of_pages()>= pageNum){
                bookArrayList.add(b);
            }
        }
        if(bookArrayList.isEmpty()){
            return null;
        }
        return bookArrayList;
    }


    public String changeStatus(String bookID, String userID){
       UserService user= new UserService();
        for(User u : user.getUsers()){
            if(u.getID().equals(userID)){
                if(u.getRole().equals("librarian")){
                    for(Book b: books){
                        if(b.getID().equals(bookID)){
                            b.setAvailable(false);
                            return "true";
                        }
                    }return "Book Not found";

                }return "Not librarian";
            }return "User ID not found";
        }
        return user.getUsers().toString();

    }


}
