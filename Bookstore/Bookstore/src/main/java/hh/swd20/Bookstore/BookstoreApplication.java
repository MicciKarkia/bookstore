package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(CategoryRepository cRepository, BookRepository bRepository) {
		return (args) -> {
			log.info("save some categories");
			cRepository.save(new Category("Sci-fi"));
			cRepository.save(new Category("Comic"));
			cRepository.save(new Category("Realistic Fiction"));
			cRepository.save(new Category("Fairytale"));
			cRepository.save(new Category("Fantasy"));
			
			log.info("fetch all categories");
			for (Category category : cRepository.findAll()) {
				log.info(category.toString());
			}
			
			log.info("saved a couple of books");
			bRepository.save(new Book("Den svavelgula himlen", "Kjell Westö", 2017, "9789515243249", 14.80, cRepository.findByName("Realistic Fiction").get(0)));
			bRepository.save(new Book("Jaga vatten", "Ellen Strömberg", 2018, "9789515244222", 22.80, cRepository.findByName("Realistic Fiction").get(0)));
			bRepository.save(new Book("Det handlar om dig", "Sandra Beijer", 2015, "9789129696332", 10.90, cRepository.findByName("Realistic Fiction").get(0)));
			bRepository.save(new Book("Min vän Lage", "Eva Lindström", 2001, "9789150100457", 12.90, cRepository.findByName("Fairytale").get(0) ));
			bRepository.save(new Book("Harry Potter och fången från Azkaban", "J.K. Rowling", 2019, "9789129723953", 20.80, cRepository.findByName("Fantasy").get(0)));
			
			log.info("fetch all books");
			for (Book book : bRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
