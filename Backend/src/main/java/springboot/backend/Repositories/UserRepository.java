package springboot.backend.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import springboot.backend.Models.User;
import static springboot.backend.Repositories.Queries.SQL_USER_TO_DB;
import static springboot.backend.Repositories.Queries.SQL_CHECK_USER;
import static springboot.backend.Repositories.Queries.SQL_DEL_USER;
import static springboot.backend.Repositories.Queries.SQL_CHANGE_PW;
import static springboot.backend.Repositories.Queries.SQL_CHECK_USERNAME;

import java.io.IOException;
import java.util.Optional;

@Repository
public class UserRepository{
    
    @Autowired
    private JdbcTemplate template;

    public Optional<String> findByUsername(String username){
        String res = template.queryForObject(SQL_CHECK_USERNAME, new SingleColumnRowMapper<>(String.class),
        username);
        return Optional.ofNullable(res);
    }
    
    public boolean save(User user) throws IOException{
        int status = template.update(SQL_USER_TO_DB, user.getUsername(), user.getPassword(), user.getEmail()
        // user.getPicture(), user.getRole()
        );
        return status > 0;
    }

    public boolean checkUser(User user){
        System.out.println(user.getUsername());
        String username = template.queryForObject(SQL_CHECK_USER, new SingleColumnRowMapper<>(String.class), 
        user.getUsername(), user.getPassword());   
        return username != null ? true : false;
    }

    public boolean deleteUser(User user){
        Integer status = template.update(SQL_DEL_USER, user.getUsername());
        return status > 0;
    }

    public boolean changePW(User user, String nPW){
        Integer status = 0;
        try {
            if(nPW == null || nPW.isEmpty()) {
                throw new IllegalArgumentException("New password cannot be null or empty");
            }
            status = template.update(SQL_CHANGE_PW, user.getPassword(), nPW, user.getUsername(), user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status != 0 ? true : false;
    }

}
