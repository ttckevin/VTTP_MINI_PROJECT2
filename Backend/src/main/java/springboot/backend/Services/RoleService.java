package springboot.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.backend.Models.Role;
import springboot.backend.Repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;
    
    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }

}