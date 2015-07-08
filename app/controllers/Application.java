package controllers;

import com.ecwid.mailchimp.MailChimpException;
import play.*;
import play.data.Form;
import play.mvc.*;

import util.Mailchimp;
import util.Mailchimp.EarlyAccessRegistration;
import views.html.*;

import java.io.File;
import java.io.IOException;

public class Application extends Controller {
    public static final Form<EarlyAccessRegistration> myForm = Form.form(EarlyAccessRegistration.class);

    public static Result index() {
        return ok(index.render());
    }

    public static Result map() {
        return ok(map.render());
    }

    public static Result sqlMap() {
        return ok(views.html.sql.sql_map.render());
    }

    public static Result earlyBird() { return ok(views.html.early_bird.render()); }

    public static Result signUp() throws MailChimpException, IOException {
        Form<EarlyAccessRegistration> boundForm = myForm.bindFromRequest();
        EarlyAccessRegistration info = boundForm.get();
        Mailchimp.subscribe(info.firstName, info.lastName, info.emailAddress);
        return redirect("/thanks");
    }

    public static Result thanks() {
        return ok(views.html.thanks.render());
    }

    public static Result upload() {
        File file = request().body().asRaw().asFile();
        Logger.info(file.getName());
        return ok("File uploaded");
    }






}
