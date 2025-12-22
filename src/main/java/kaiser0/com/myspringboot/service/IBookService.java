package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.request.BookRequest;
import kaiser0.com.myspringboot.dto.response.BookResponse;

import java.util.List;

public interface IBookService {
    BookResponse saveBook(BookRequest bookRequest);

    List<BookResponse> getAllBooks();
}