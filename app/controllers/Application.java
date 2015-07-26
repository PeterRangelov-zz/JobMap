package controllers;

import com.ecwid.mailchimp.MailChimpException;
import models.User;
import play.*;
import play.api.mvc.Flash;
import play.data.Form;
import play.mvc.*;

import util.Mailchimp;
import util.Mailchimp.EarlyAccessRegistration;
import views.html.*;
import models.User.SigninForm;
import models.User.Role;

import java.io.File;
import java.io.IOException;

public class Application extends Controller {
    public static final Form<EarlyAccessRegistration> mailchimpForm = Form.form(EarlyAccessRegistration.class);
    public static final Form<SigninForm> signinForm = Form.form(SigninForm.class);


    public static Result signUp() throws MailChimpException, IOException {
        Form<EarlyAccessRegistration> boundForm = mailchimpForm.bindFromRequest();
        EarlyAccessRegistration info = boundForm.get();
        Mailchimp.subscribe(info.firstName, info.lastName, info.emailAddress);
        return redirect("/thanks");
    }

    public static Result authenticateUser() {
        SigninForm form = signinForm.bindFromRequest().get();
        Logger.info(form.emailAddress);
        Logger.info(form.password);
        // SEARCH EBEAN FOR USER
        try {
            User user = User.findByEmail(form.emailAddress);
            Logger.info(user.toString());

            if (! user.passwordHash.equalsIgnoreCase(form.password)) {
                flash("error", "Wrong password");
                return redirect("/signin");
            }
            if (user.accountLocked) {
                flash("error", "Your account is locked");
                return redirect("/signin");
            }
//
            if (user.role.equals(Role.ADMIN)) {
                return redirect("/admin");
            }
            if (user.role.equals(Role.APPLICANT)) {
                return redirect("/applicant");
            }
            if (user.role.equals(Role.RECRUITER)) {
                return redirect("/recruiter");
            }

            return redirect("/signin");



        }
        catch (NullPointerException e) {
            flash("error", "Username or password incorrect");
            return redirect("/signin");
        }



        // CHECK USER ROLE

        // STORE USER IN SESSION


    }

    public static Result upload() {
        File file = request().body().asRaw().asFile();
        Logger.info(file.getName());
        return ok("File uploaded");
    }






}
