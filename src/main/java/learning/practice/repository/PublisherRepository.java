package learning.practice.repository;

import learning.practice.model.Book;
import learning.practice.model.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

    @Query("select model.books from Publisher model")
    Set<Book> findAllBooks();
}
