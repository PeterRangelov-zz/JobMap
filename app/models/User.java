package models;

import com.avaje.ebean.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class User extends Model {
    @Id
    public Long id;

    public String firstName;
    public String lastName;

    @NotNull @Constraints.Required @Constraints.Email
    public String emailAddress;

    @Column(name = "pwd_hash")
    public String passwordHash;

    public DateTime lastLogin;

    public boolean accountLocked;

    public String validationToken;

    public boolean accountValidated;

    @Constraints.Required @Enumerated(value= EnumType.STRING)
    public Role role;

    @Constraints.Required @Enumerated(value= EnumType.STRING)
    public Plan plan = Plan.PLAN1;

    @Column(length = 100)
    public String stripeToken;

    public static Finder<Long, User> find = new Finder<>(Long.class, User.class);

    public enum Plan {
        FREE, PLAN1, PLAN2, PLAN3
    }

    public enum Role {
        @EnumValue("R") RECRUITER,
        @EnumValue("A") APPLICANT,
        @EnumValue("X") ADMIN
    }

    public static class SigninForm {
        public String emailAddress;
        public String password;
    }

    public static User findByEmail (String email) {
        return User.find.where().eq("emailAddress", email).findUnique();
    }

}




