package hh.swd20.Bookstore.domain;

import hh.swd20.Bookstore.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByName(String name);
}
