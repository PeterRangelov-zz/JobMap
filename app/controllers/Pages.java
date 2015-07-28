package controllers;

import models.User.SigninForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.security.annotations.Admin;
import views.html.*;
import views.html.admin_area.*;
import views.html.public_area.*;

public class Pages extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result map() {
        return ok(map.render());
    }

    public static Result earlyBird() { return ok(early_bird.render()); }

    public static Result signIn() {
        return ok(signin.render(Form.form(SigninForm.class)));
    }

    @Admin
    public static Result adminHome() {
        return ok(admin_home.render()); }
    public static Result recruiterHome() { return TODO; }
    public static Result applicantHome() {
        return TODO;
    }



    public static Result thanks() {
        return ok(thanks.render());
    }








}
