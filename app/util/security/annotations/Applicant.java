package util.security.annotations;

import play.mvc.With;
import util.security.filters.AdminRoleChecker;
import util.security.filters.ApplicantRoleChecker;
import util.security.filters.AuthenticatorChecker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@With({AuthenticatorChecker.class, ApplicantRoleChecker.class})
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Applicant {

}