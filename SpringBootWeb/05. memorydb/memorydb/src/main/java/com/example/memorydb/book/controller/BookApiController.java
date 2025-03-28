package com.example.memorydb.book.controller;

import com.example.memorydb.book.model.BookEntity;
import com.example.memorydb.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/book")
@RequiredArgsConstructor
public class BookApiController {
    private final BookService bookService;

    @PutMapping("")
    public BookEntity create(
            @RequestBody BookEntity bookEntity
    ){
        return bookService.create(bookEntity);
    }

    @GetMapping("/all")
    public List<BookEntity> findAll(
        @RequestParam(name = "id", defaultValue = "0") Long id
    ) {
        return bookService.findALl();
    }

   /* @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
*/
}
