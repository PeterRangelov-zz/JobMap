package util;

import com.avaje.ebean.Ebean;
import com.ecwid.mailchimp.MailChimpClient;
import com.ecwid.mailchimp.MailChimpException;
import com.ecwid.mailchimp.MailChimpObject;
import com.ecwid.mailchimp.method.v2_0.lists.Email;
import com.ecwid.mailchimp.method.v2_0.lists.SubscribeMethod;
import models.Group;
import models.Site;
import play.Logger;
import util.Env.Variable;

import java.io.IOException;
import java.util.List;

/**
 * Created by peterrangelov on 6/30/15.
 */
public class Startup {

    public static void  cleanDB () {
        Ebean.beginTransaction();
        List<Site> sites = Ebean.find(Site.class).findList();
        List<Group> groups = Ebean.find(Group.class).findList();
        Ebean.delete(groups);
        Ebean.delete(sites);
        Ebean.endTransaction();
    }


}
