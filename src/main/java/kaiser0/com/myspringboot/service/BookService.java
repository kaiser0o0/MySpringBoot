package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.request.BookRequest;
import kaiser0.com.myspringboot.dto.response.BookResponse;
import kaiser0.com.myspringboot.entity.Book;
import kaiser0.com.myspringboot.mapper.BookMapper;
import kaiser0.com.myspringboot.repository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Constructor injection için Lombok
public class BookService implements IBookService {

    private final IBookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponse saveBook(BookRequest bookRequest) {
        // 1. DTO'yu Entity'ye çevir
        Book book = bookMapper.toEntity(bookRequest);

        // 2. Veritabanına kaydet
        Book savedBook = bookRepository.save(book);

        // 3. Entity'yi Response DTO'ya çevirip dön
        return bookMapper.toResponse(savedBook);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();


        return books.stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }
}