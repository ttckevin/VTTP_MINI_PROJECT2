package springboot.backend.Controllers;

import springboot.backend.Models.Role;
import springboot.backend.Models.User;
import springboot.backend.Repositories.RoleRepository;
import springboot.backend.Security.JwtRequest;
import springboot.backend.Security.JwtResponse;
import springboot.backend.Services.JwtService;
import springboot.backend.Services.MailService;
import springboot.backend.Services.RoleService;
import springboot.backend.Services.UserService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("api")
public class AuthenticationController {
  
    @Autowired
    private RoleService roleService;

    @PostMapping("/createRole")
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }

    
    @Autowired
    private UserService userSvc;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private MailService mailSvc;

    @PostConstruct
    public void initRolesAndUsers() {
        userSvc.initRolesAndUser();
    }
    
    @PostMapping("/registerUser")
    public User registerNewUser(@RequestBody User user) {
        Role role = roleRepo.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        String welcome = "Congratulations on taking your first step, " + user.getUsername();
        try {
            mailSvc.sendEmail(user.getEmail(), "Welcome" , welcome);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return userSvc.registerNewUser(user);
    }

    @GetMapping("/forAdmins")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "This URL is only accessible to admin users.";
    }

    @GetMapping("/forUsers") 
    @PreAuthorize("hasRole('User')")
    public String forUsers() {
        return "This URL is accessible to all users.";
    }

    @Autowired
    private JwtService jwtService;

    @PostMapping(path="/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest request) throws Exception {
        return jwtService.createJwtToken(request);
    }
}
