package util.security;

import lombok.NoArgsConstructor;
import models.User;
import play.Logger;
import play.data.Form;
import play.libs.F;
import play.mvc.*;
import views.html.public_area.signin;
import play.mvc.Action.Simple;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AuthChecker {
    @With({AuthenticatorChecker.class, AdminRoleChecker.class})  @Target({ElementType.TYPE, ElementType.METHOD})  @Retention(RetentionPolicy.RUNTIME)
    public @interface Admin {}

    @With({AuthenticatorChecker.class, ApplicantRoleChecker.class}) @Target({ElementType.TYPE, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
    public @interface Applicant {}

    @With({AuthenticatorChecker.class, RecruiterRoleChecker.class}) @Target({ElementType.TYPE, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
    public @interface Recruiter {}

    @With(PaymentPlanChecker.class) @Target({ElementType.TYPE, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
    public @interface PaidFeature {
        User.Plan[] plans() default User.Plan.PLAN1;
    }

    public static class AuthenticatorChecker extends Simple {
        public F.Promise<Result> call(Http.Context ctx) throws Throwable {
            try {
                Logger.debug("AuthenticatorChecker called " + ctx);
                Logger.debug("ctx.session().size() ---- > " + ctx.session().size());
                Logger.debug("Session role: " + ctx.session().get("role"));
                Logger.debug("Session email: " + ctx.session().get("email"));
                Logger.debug("Session userid: " + ctx.session().get("id"));
                ctx.session().get("id");
                ctx.session().get("email");
                ctx.session().get("role");
                return delegate.call(ctx);
            }
            catch (NullPointerException e) {
                Logger.debug(this.getClass() + "called. NullPointerExceltion catch statement");
                return F.Promise.pure(Results.ok(signin.render(Form.form(User.SigninForm.class))));
            }
        }
    }

    public static class AdminRoleChecker extends Simple {
        public F.Promise<Result> call(Http.Context ctx) throws Throwable {
            if (ctx.session().get("role").equalsIgnoreCase(User.Role.ADMIN.toString())) { return delegate.call(ctx); }
            else { return F.Promise.pure(Results.unauthorized("Unauthorized")); }
        }
    }

    public static class ApplicantRoleChecker extends Simple {
        public F.Promise<Result> call(Http.Context ctx) throws Throwable {
            if (ctx.session().get("role").equalsIgnoreCase(User.Role.APPLICANT.toString())) { return delegate.call(ctx); }
            else { return F.Promise.pure(Results.unauthorized("Unauthorized")); }
        }
    }

    public static class RecruiterRoleChecker extends Action.Simple {
        public F.Promise<Result> call(Http.Context ctx) throws Throwable {
            if (ctx.session().get("role").equalsIgnoreCase(User.Role.RECRUITER.toString())) { return delegate.call(ctx); }
            else { return F.Promise.pure(Results.unauthorized("Unauthorized")); }
        }
    }

    public static class PaymentPlanChecker extends Action.Simple {
        public F.Promise<Result> call(Http.Context ctx) throws Throwable {
            Logger.debug("Calling action for " + ctx);
            if (1==1) {
                return delegate.call(ctx);
            }
            else {
                return F.Promise.pure(Results.unauthorized("Unauthorized"));
            }
        }
    }

}
