package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import play.Logger;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import util.misc.Env;
import util.misc.Mailer;
import util.validation.JobmapValidator.EmailInDB;
import util.validation.JobmapValidator.*;
import util.misc.Env.Variable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class User extends Model {
    @Id
    public Long id;

    @Column(length = 100)
    public String firstName;

    @Column(length = 100)
    public String lastName;

    @NotNull @Required @Column(length = 100, unique = true)
    public String emailAddress;

    @NotNull @Required @Column(name = "pwd_hash", length = 128)
    public String passwordHash;

    public DateTime lastLogin;

    public boolean accountLocked;

    @Column(length = 30)
    public String validationToken;

    public boolean accountActivated;

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
        @EmailInDB
        public String emailAddress;

        @Required(message = "Password is required")
        public String password;

        public String validate() {
            if (verifyPassword(emailAddress, password)) {
                return null;
            }
            return "Invalid password";
        }
    }

    public static class RegisterForm {
        @Email @EmailNotInDB
        public String emailAddress;

        @Required(message = "Password is required") @MinLength(value = 6, message = "Password must be at least 6 characters")
        public String password;

        @Required
        public String passwordConfirm;

        @Required(message = "Please select a role")
        public Role role;

        public String validate () {
            if (password.equals(passwordConfirm)) {
                return null;
            }
            return "Passwords must match";
        }
    }

    public static User findByEmail (String email) {
        return User.find.where().eq("emailAddress", email).findUnique();
    }

    public static boolean emailExists (String email) {
        if (findByEmail(email) != null) {
            return true;
        }
        else {
            return false;
        }

    }

    public static boolean verifyPassword (String email, String password) {
        return findByEmail(email).passwordHash.equals(DigestUtils.sha512Hex(password+Env.get(Variable.PWD_SALT)));
    }

    public static void registerUser (String emailAddress, String password, Role role) {
        String token = RandomStringUtils.randomAlphabetic(30);
        Logger.debug("registering user...");
        Logger.debug(emailAddress);
        Logger.debug(password);
        Logger.debug(role.toString());
        Logger.debug(token);
        User u = new User();
        u.setEmailAddress(emailAddress);
        u.setPasswordHash(DigestUtils.sha512Hex(password+Env.get(Variable.PWD_SALT)));
        u.setRole(role);
        u.setAccountActivated(false);
        u.setValidationToken(token);
        Logger.debug(u.toString());
        // SEND EMAIL
        Mailer.sendActivationToken(u.getEmailAddress(), token);
        Ebean.save(u);
    }

    public static void activateAccount (String token) {
        User u = User.find.where().eq("validationToken", token).findUnique();
        u.setAccountActivated(true);
        u.setValidationToken(null);
        Logger.info(u.toString());
        Mailer.sendActivationConfirmation(u.getEmailAddress());

        Ebean.save(u);

    }

}




