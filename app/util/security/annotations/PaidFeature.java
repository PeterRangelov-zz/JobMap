package util.security.annotations;

import models.User;
import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import models.User.Plan;
import util.security.filters.AuthenticatorChecker;
import util.security.filters.PaymentPlanChecker;

@With(PaymentPlanChecker.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PaidFeature {
    Plan[] plans() default User.Plan.PLAN1;
}