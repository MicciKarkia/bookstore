package hh.swd20.Bookstore.domain;

import hh.swd20.Bookstore.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

	Book findByIsbn(String isbn);
	
	List<Book> findByTitleContaining(String title);
}
