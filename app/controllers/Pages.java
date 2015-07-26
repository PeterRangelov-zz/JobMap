package controllers;

import com.ecwid.mailchimp.MailChimpException;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.Mailchimp;
import util.Mailchimp.EarlyAccessRegistration;
import views.html.*;
import views.html.admin_area.*;
import play.api.mvc.Flash;
import java.io.File;
import java.io.IOException;

public class Pages extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result map() {
        return ok(map.render());
    }

    public static Result sqlMap() {
        return ok(views.html.sql.sql_map.render());
    }

    public static Result earlyBird() { return ok(early_bird.render()); }

    public static Result signIn() {
        return ok(signin.render());
    }

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
