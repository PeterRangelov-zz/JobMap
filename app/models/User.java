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
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class User extends Model {
    @Id
    public Long id;

    public String firstName;
    public String lastName;

    @NotNull @Required @Email
    public String emailAddress;

    @Column(name = "pwd_hash")
    public String passwordHash;

    public DateTime lastLogin;

    public boolean accountLocked;

    public String validationToken;

    public boolean accountValidated;

    @Required @Enumerated(value= EnumType.STRING)
    public Role role;

    @Required @Enumerated(value= EnumType.STRING)
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
        @Required(message = "Email is required") @Email(message = "Please enter a valid email address")
        public String emailAddress;

        @Required(message = "Password is required") @MinLength(value = 6, message = "Password must be at least 6 characters")
        public String password;
    }

    public static User findByEmail (String email) {
        return User.find.where().eq("emailAddress", email).findUnique();
    }

}




