package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result map() {
        return ok(map.render());
    }

    public static Result sqlMap() {
        return ok(views.html.sql.sql_map.render());
    }

}
