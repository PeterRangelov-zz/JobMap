package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User extends Model {
    @Id
    public Long id;

    public String firstName;

    public String lastName;

    public String emailAddress;



    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);
}
