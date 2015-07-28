package util.security.filters;

import models.User.SigninForm;
import play.Logger;
import play.data.Form;
import play.libs.F.Promise;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;
import views.html.public_area.signin;

public class AuthenticatorChecker extends Simple {

    public Promise<Result> call(Context ctx) throws Throwable {
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
            return Promise.pure(Results.ok(signin.render(Form.form(SigninForm.class))));
        }

    }
}
