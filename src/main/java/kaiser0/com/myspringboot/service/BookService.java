package kaiser0.com.myspringboot.service;

import kaiser0.com.myspringboot.dto.request.BookRequest;
import kaiser0.com.myspringboot.dto.response.BookResponse;
import kaiser0.com.myspringboot.entity.Book;
import kaiser0.com.myspringboot.entity.User;
import kaiser0.com.myspringboot.mapper.BookMapper;
import kaiser0.com.myspringboot.repository.IBookRepository;
import kaiser0.com.myspringboot.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final IBookRepository bookRepository;
    private final IUserRepository userRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BookResponse saveBook(BookRequest bookRequest) {


        User user = userRepository.findById(bookRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı! ID: " + bookRequest.getUserId()));


        Book book = bookMapper.toEntity(bookRequest);


        book.setUser(user);


        Book savedBook = bookRepository.save(book);


        return bookMapper.toResponse(savedBook);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }
}