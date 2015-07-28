package util.security.filters;

import models.User;
import play.Logger;
import play.data.Form;
import play.libs.F.Promise;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;
import views.html.public_area.signin;
import models.User.SigninForm;

/**
 * Created by peterrangelov on 7/26/15.
 */
public class ApplicantRoleChecker extends Simple {

public Promise<Result> call(Context ctx) throws Throwable {
        if (ctx.session().get("role").equalsIgnoreCase(User.Role.APPLICANT.toString())) {
            return delegate.call(ctx);
        }
        else {
            return Promise.pure(Results.unauthorized("Unauthorized"));
        }
    }

}
