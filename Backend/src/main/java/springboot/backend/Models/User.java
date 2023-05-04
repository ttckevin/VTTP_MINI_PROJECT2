package springboot.backend.Models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User{

    @Id
    private String username;
    private String password;
    private String email;

    // @JsonDeserialize(using = BlobDeserializer.class)
    // private Blob picture;    
    
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns = {@JoinColumn(name="user_id")}, 
    inverseJoinColumns = {@JoinColumn(name="role_id")})
    private Set<Role> roles = new HashSet<Role>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String email) {
        this.email = email;
    }
    public User() {
    }

    public Set<Role> getRoles() {return roles;}
    public void setRoles(Set<Role> roles) {this.roles = roles;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    // public Blob getPicture() {return picture;}
    // public void setPicture(Blob picture) {this.picture = picture;}    

}
