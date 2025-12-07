package kaiser0.com.myspringboot.controller;

import jakarta.validation.Valid;
import kaiser0.com.myspringboot.dto.request.BookRequest;
import kaiser0.com.myspringboot.dto.response.BookResponse;
import kaiser0.com.myspringboot.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @PostMapping

    public BookResponse createBook(@Valid @RequestBody BookRequest bookRequest) {
        return bookService.saveBook(bookRequest);
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }
}