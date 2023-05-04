package springboot.backend.Repositories;

public class Queries {
    
    public static final String SQL_GET_COL = "SELECT * FROM col WHERE City = ?";
    public static final String SQL_USER_TO_DB = "insert into user (username, password, email, picture) values(?, ?, ?, ?)";
    public static final String SQL_CHECK_USER = "SELECT username FROM user WHERE username= ? AND password= ? ";
    public static final String SQL_CHECK_USERNAME = "SELECT username FROM user WHERE username= ?";
    public static final String SQL_DEL_USER = "DELETE FROM user WHERE username = ?";
    public static final String SQL_CHANGE_PW = """
            UPDATE user 
            SET password = REPLACE (password, ?, ?) 
            WHERE 
            username = ?
            AND 
            email = ?
            """;
}
