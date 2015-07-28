package util.security.filters;

import models.User;
import play.Logger;
import play.libs.F.Promise;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;
import views.html.public_area.signin;

/**
 * Created by peterrangelov on 7/26/15.
 */
public class RecruiterRoleChecker extends Simple {

    public Promise<Result> call(Context ctx) throws Throwable {

        if (ctx.session().get("role").equalsIgnoreCase(User.Role.RECRUITER.toString())) {
            return delegate.call(ctx);
        } else {
            return Promise.pure(Results.unauthorized("Unauthorized"));
        }
    }

}