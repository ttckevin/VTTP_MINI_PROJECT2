package springboot.backend.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amadeus.exceptions.ResponseException;

import jakarta.json.Json;
import jakarta.mail.MessagingException;
import springboot.backend.Models.User;
import springboot.backend.Services.COLService;
import springboot.backend.Services.FlightService;
import springboot.backend.Services.ImageService;
import springboot.backend.Services.MailService;
import springboot.backend.Services.UserService;

@Controller
@RequestMapping("api")
public class TheController {
    
    @Autowired
    private COLService colSvc;

    @Autowired
    private ImageService imgSvc;

    @Autowired
    private FlightService flightSvc;

    @Autowired 
    private UserService userSvc;

    @Autowired
    private MailService mailSvc;

    @GetMapping
    @RequestMapping("/city-search")
    @ResponseBody
    public ResponseEntity<String> citySearch(@RequestParam String city) throws IOException{
        System.out.println("Here at least + city: "  + city);
        List<String> test = imgSvc.getImages(city); 
      return ResponseEntity.ok().body(colSvc.getCOL(city).toJson(test).toString());
    }

    @GetMapping
    @RequestMapping("/flight-search")
    @ResponseBody
    public ResponseEntity<String> flightSearch() throws IOException, ResponseException{
      return ResponseEntity.ok().body(flightSvc.test("SIN", "OSL", "2023-11-09", 15, 1));
    }

    @PostMapping(path="/signup-user", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, 
                produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createAcc(@RequestPart("username") String username, @RequestPart("email") String email,
    @RequestPart("password") String password, @RequestPart("picture") MultipartFile picture) throws IOException, SQLException, MessagingException{
      mailSvc.sendEmail(email, "Welcome", "Test");
      // userSvc.userToDB(username, password, email, picture);
      return ResponseEntity.ok().body(Json.createObjectBuilder().add("username",username).build().toString());
    }

    // @PostMapping(path="/login-user", produces=MediaType.APPLICATION_JSON_VALUE)
    // @ResponseBody
    // public ResponseEntity<String> loginAcc(@RequestBody User user) throws IOException, SQLException{
    //     if(userSvc.checkUser(user)){
    //       System.out.println("User logged in");
    //       return ResponseEntity.ok().body(Json.createObjectBuilder().add("username",user.getUsername()).build().toString());
    //     }
    //     return ResponseEntity.badRequest().body("Error");
    // }

    @PostMapping(path="/delete-user", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> DeleteAcc(@RequestBody User user) throws IOException, SQLException{
        if(userSvc.checkUser(user)){
          if(userSvc.deleteUser(user))
          return ResponseEntity.ok().body("[Deleted]: "  + user.getUsername());
        }
        return ResponseEntity.badRequest().body("Error");
    }
    
    @PutMapping(path="/pwChange-user", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> changePW(@RequestBody Map<String, String> request) throws IOException, SQLException{
        User user = new User();
        user.setUsername(request.get("username"));
        user.setPassword(request.get("password"));
        user.setEmail(request.get("email"));
        if(userSvc.checkUser(user)){
          if(userSvc.changePW(user, request.get("newPW")))
          return ResponseEntity.ok().body("[Success]: "  + user.getUsername());
        }
        return ResponseEntity.badRequest().body("Error");
    }
}
