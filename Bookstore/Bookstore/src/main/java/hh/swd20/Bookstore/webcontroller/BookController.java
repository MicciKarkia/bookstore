package hh.swd20.Bookstore.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
	
	@GetMapping("/index")
	public String allBooksView() {
		return "/bookstore";
	}

}
