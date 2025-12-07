package kaiser0.com.myspringboot.mapper;

import kaiser0.com.myspringboot.dto.request.BookRequest;
import kaiser0.com.myspringboot.dto.response.BookResponse;
import kaiser0.com.myspringboot.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {


    public Book toEntity(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setpageCount(request.getPageCount());
        return book;
    }


    public BookResponse toResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPageCount(book.getPageCount());
        return response;
    }
}