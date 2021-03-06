package hh.swd20.Bookstore.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.CategoryRepository;
import java.util.List;
import java.util.Optional;

@Controller
public class BookController {
	//Kaksi eri kytkentää tarvitaan, koska haetaan kahdesta eri paikasta
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryrepository;
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/booklist")
	public String getAllBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	
	//REST service to get all books
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepository.findAll();
	}
	
	//REST service to get book by id
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return bookRepository.findById(bookId);
	}

	@RequestMapping(value = "/add")
	public String getNewForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryrepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String delete(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/edit/{id}")
	public String getEditForm(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryrepository.findAll());
		return "editbook";
	}

	@RequestMapping(value = "/cancel")
	public String cancel() {
		return "redirect:booklist";
	}

	@GetMapping("/")
	public String allBooksView() {
		return "bookstore";
	}

}
