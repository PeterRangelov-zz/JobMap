package util;

import com.ecwid.mailchimp.MailChimpClient;
import com.ecwid.mailchimp.MailChimpException;
import com.ecwid.mailchimp.MailChimpObject;
import com.ecwid.mailchimp.method.v2_0.lists.Email;
import com.ecwid.mailchimp.method.v2_0.lists.SubscribeMethod;
import play.Logger;

import java.io.IOException;

/**
 * Created by peterrangelov on 6/30/15.
 */
public class Mailchimp {

    public static void subscribe(String firstName, String lastName, String emailAddress) throws MailChimpException, IOException {
        MailChimpClient mailChimpClient = new MailChimpClient();
        Logger.info("Subscribing...");



        SubscribeMethod subscribeMethod = new SubscribeMethod();
        subscribeMethod.apikey = System.getProperties().getProperty("MAILCHIMP_API_KEY");
        subscribeMethod.id = System.getProperties().getProperty("MAILCHIMP_LIST_ID");
        subscribeMethod.email = new Email();
        subscribeMethod.email.email = emailAddress;
        subscribeMethod.double_optin = false;
        subscribeMethod.update_existing = true;
        subscribeMethod.merge_vars = new MergeVars(emailAddress, firstName, lastName);
        mailChimpClient.execute(subscribeMethod);
    }

    public static class MergeVars extends MailChimpObject {

        @MailChimpObject.Field
        public String EMAIL, FNAME, LNAME;

        public MergeVars() {
        }

        public MergeVars(String email, String fname, String lname) {
            this.EMAIL = email;
            this.FNAME = fname;
            this.LNAME = lname;
        }
    }

    public static class EarlyAccessRegistration {
        public String firstName;
        public String lastName;
        public String emailAddress;
    }
}
