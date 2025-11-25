package kaiser0.com.myspringboot.controller;

import kaiser0.com.myspringboot.entity.Book;
import kaiser0.com.myspringboot.service.IBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books") // localhost:8000/books
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;

    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {

        return bookService.saveBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

}
