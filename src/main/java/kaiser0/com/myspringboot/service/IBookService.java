package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.BookDTO;
import java.util.List;

public interface IBookService {
    BookDTO saveBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
}