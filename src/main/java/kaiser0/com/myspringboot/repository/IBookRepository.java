package kaiser0.com.myspringboot.repository;

import kaiser0.com.myspringboot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
}
