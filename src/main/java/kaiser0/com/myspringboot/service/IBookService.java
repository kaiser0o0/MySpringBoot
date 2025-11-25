package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.entity.Book;

import java.util.List;

public interface IBookService {

    Book saveBook(Book book);

    List<Book> getAllBooks();
}