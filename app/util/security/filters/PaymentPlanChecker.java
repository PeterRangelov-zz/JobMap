package util.security.filters;

import play.Logger;
import play.libs.F.Promise;
import play.mvc.Action.Simple;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;

public class PaymentPlanChecker extends Simple {

public Promise<Result> call(Context ctx) throws Throwable {
    Logger.info("Calling action for " + ctx);

    if (1==1) {
        return delegate.call(ctx);
    }
    else {
        return Promise.pure(Results.unauthorized("Unauthorized"));
    }

    }
}