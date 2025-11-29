package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.BookDTO;
import kaiser0.com.myspringboot.entity.Book;
import kaiser0.com.myspringboot.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    private final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) {
        // 1. DTO'dan Entity'ye çevir (Veritabanına kaydetmek için)
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setpageCount(bookDTO.getPageCount()); // Senin entity'de setpageCount küçük harfle yazılmış

        // 2. Veritabanına kaydet
        Book savedBook = bookRepository.save(book);

        // 3. Entity'den DTO'ya çevir (Controller'a geri dönmek için)
        BookDTO responseDTO = new BookDTO();
        responseDTO.setId(savedBook.getId());
        responseDTO.setTitle(savedBook.getTitle());
        responseDTO.setAuthor(savedBook.getAuthor());
        responseDTO.setPageCount(savedBook.getPageCount());

        return responseDTO;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        // Listeyi stream ile dönüp her bir Book nesnesini BookDTO'ya çeviriyoruz
        return books.stream().map(book -> {
            BookDTO dto = new BookDTO();
            dto.setId(book.getId());
            dto.setTitle(book.getTitle());
            dto.setAuthor(book.getAuthor());
            dto.setPageCount(book.getPageCount());
            return dto;
        }).collect(Collectors.toList());
    }
}