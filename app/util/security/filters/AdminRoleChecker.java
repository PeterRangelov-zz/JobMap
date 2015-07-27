package util.security.filters;

import models.User;
import play.Logger;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;
import models.User.Role;
import play.mvc.With;
import views.html.public_area.signin;

public class AdminRoleChecker extends Simple {

    public Promise<Result> call(Context ctx) throws Throwable {
        Logger.debug("Calling action for " + ctx);
        Logger.debug("ctx.session().size() ---- > " + ctx.session().size());
        Logger.debug("Session firstName: " + ctx.session().get("firstName"));
        Logger.debug("Session role: " + ctx.session().get("role"));

        if (ctx.session().get("role").equalsIgnoreCase(Role.ADMIN.toString())) {
            return delegate.call(ctx);
        } else {
            return Promise.pure(Results.unauthorized("Unauthorized"));
        }


    }
}
