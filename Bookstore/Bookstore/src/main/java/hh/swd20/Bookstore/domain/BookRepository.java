package hh.swd20.Bookstore.domain;

import hh.swd20.Bookstore.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long> {

	Book findByIsbn(String isbn);
	
	List<Book> findByTitleContaining(String title);
	
	List<Book> findByAuthor(@Param("author") String author);
}
