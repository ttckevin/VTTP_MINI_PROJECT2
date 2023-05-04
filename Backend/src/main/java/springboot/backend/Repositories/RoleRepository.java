package springboot.backend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot.backend.Models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    
}