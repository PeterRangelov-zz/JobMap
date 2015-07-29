package util.validation;

import models.User;
import org.apache.commons.validator.routines.EmailValidator;
import play.Logger;
import play.data.Form;
import play.data.Form.Display;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Validator;
import play.libs.F;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.commons.validator.routines.EmailValidator;

public class JobmapValidator {
    @Target({ElementType.FIELD})  @Retention(RetentionPolicy.RUNTIME)  @Constraint(validatedBy = {EmailMustExistValidator.class})  @Display(name = "constraint.email", attributes = {})
    public @interface EmailInDB {
        String message() default "Email not found. Did you register using a different one?";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class EmailMustExistValidator extends Validator<String> implements ConstraintValidator<EmailInDB, String> {
        public void initialize(EmailInDB var1) {}

        public boolean isValid(String email) {
            Logger.debug(this.getClass().getCanonicalName());
            Logger.debug("User entered: " + email);
            return User.emailExists(email);
        }

        public F.Tuple<String, Object[]> getErrorMessageKey() {
            return F.Tuple("error.email", new Object[0]);
        }
    }

    @Target({ElementType.FIELD})  @Retention(RetentionPolicy.RUNTIME)  @Constraint(validatedBy = {EmailMustNotExistValidator.class})  @Display(name = "constraint.email", attributes = {})
    public @interface EmailNotInDB {
        String message() default "Email is already registered with Jobmap.";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class EmailMustNotExistValidator extends Validator<String> implements ConstraintValidator<EmailNotInDB, String> {
        public void initialize(EmailNotInDB var1) {}

        public boolean isValid(String email) {
            Logger.debug(this.getClass().getCanonicalName());
            Logger.debug("User entered: " + email);
            return ! User.emailExists(email);
        }

        public F.Tuple<String, Object[]> getErrorMessageKey() {
            return F.Tuple("error.email", new Object[0]);
        }
    }

    @Target({ElementType.FIELD}) @Retention(RetentionPolicy.RUNTIME) @Constraint(validatedBy = {PasswordValidator.class}) @Display(name = "constraint.password", attributes = {})
    public @interface VerifyPassword {
        String message() default "Incorrect password";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class PasswordValidator extends Validator<String> implements ConstraintValidator<VerifyPassword, String> {
        public void initialize(VerifyPassword var1) {}

        public boolean isValid(String email) {
            Logger.debug(this.getClass().getCanonicalName());
            return User.emailExists(email);
        }

        public F.Tuple<String, Object[]> getErrorMessageKey() {
            return F.Tuple("error.password", new Object[0]);
        }
    }

    @Target({ElementType.FIELD})  @Retention(RetentionPolicy.RUNTIME)  @Constraint(validatedBy = {EmailFormatValidator.class})  @Display(name = "constraint.email", attributes = {})
    public @interface Email {
        String message() default "Please enter a valid email address.";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class EmailFormatValidator extends Validator<String> implements ConstraintValidator<Email, String> {
        public void initialize(Email var1) {}

        public boolean isValid(String email) {
            Logger.debug(this.getClass().getCanonicalName());
            Logger.debug("User entered: " + email);
            return EmailValidator.getInstance().isValid(email);
        }

        public F.Tuple<String, Object[]> getErrorMessageKey() {
            return F.Tuple("error.email", new Object[0]);
        }
    }










}