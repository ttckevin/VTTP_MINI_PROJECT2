package springboot.backend.Services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springboot.backend.Models.Role;
import springboot.backend.Models.User;
import springboot.backend.Repositories.RoleRepository;
import springboot.backend.Repositories.URepository;
import springboot.backend.Repositories.UserRepository;

@Service
public class UserService {

    
    @Autowired
    private URepository userRepo;

    @Autowired 
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    //consider using update or sth instead of doing the initialisation everytime
    public void initRolesAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role with elevated privileges");
        roleRepo.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default user role for newly created users");
        roleRepo.save(userRole);

        User adminUser = new User();
        adminUser.setUsername("adminUserBossMan");
        adminUser.setPassword(getEncodedPassword("adminPassword"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userRepo.save(adminUser);
    }

    public User registerNewUser(User user) {
        Role role = roleRepo.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return userRepo.save(user);
    }


    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Autowired
    private UserRepository repo;

        public boolean save(User user) throws IOException{
            return repo.save(user);
        }

        public boolean checkUser(User user){
            return repo.checkUser(user);
        }

        public boolean deleteUser(User user) {
            return repo.deleteUser(user);
        }
        
        public boolean changePW(User user, String nPW){
            return repo.changePW(user, nPW);
        }

}
