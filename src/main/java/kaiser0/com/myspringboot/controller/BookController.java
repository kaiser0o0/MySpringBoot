package kaiser0.com.myspringboot.controller;

import kaiser0.com.myspringboot.dto.BookDTO;
import kaiser0.com.myspringboot.service.IBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {

        return bookService.saveBook(bookDTO);
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {

        return bookService.getAllBooks();
    }
}