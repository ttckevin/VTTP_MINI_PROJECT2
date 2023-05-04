package springboot.backend.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import springboot.backend.Models.User;


@Repository
public interface URepository extends CrudRepository<User, String> {

}
