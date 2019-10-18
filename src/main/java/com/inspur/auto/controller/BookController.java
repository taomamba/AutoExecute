package com.inspur.auto.controller;

import com.inspur.auto.entity.po.Book;
import com.inspur.auto.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/add_index",method = RequestMethod.POST)
    public ResponseEntity<String> indexDoc(@RequestBody Book book){
        bookRepository.save(book);
        return new ResponseEntity<>("save executed!", HttpStatus.OK);
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<Iterable> getAll(){
        Iterable<Book> all = bookRepository.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Optional<Book> getBookById(@PathVariable("id")String id){
        Optional<Book> byId = bookRepository.findById(id);
        return byId;
    }


}
