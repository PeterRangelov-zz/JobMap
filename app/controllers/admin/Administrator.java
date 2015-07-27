package controllers.admin;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import util.security.annotations.Admin;
import views.html.admin_area.test;

@Admin
public class Administrator extends Controller {

    public static Result test() {


        Logger.info("ACCESS GRANTED");


        return ok(test.render());

    }


}
