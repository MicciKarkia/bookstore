package hh.swd20.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import hh.swd20.Bookstore.domain.User;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
