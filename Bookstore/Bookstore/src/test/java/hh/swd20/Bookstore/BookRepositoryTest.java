package hh.swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitleContaining("Jaga vatten");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Ellen Strömberg");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("Taltuta lievä tulehdus", "Reijo laatikainen", 2019, "9789523540682", 22.90, new Category("Self-help"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		List<Book> bookToDelete = repository.deleteByTitle("Taltuta lievä tulehdus");
		
		assertThat(bookToDelete).isEmpty();
	}
	

}
