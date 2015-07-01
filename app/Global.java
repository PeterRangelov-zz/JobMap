import com.avaje.ebean.Ebean;
import models.Hospital;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.Play;
import play.libs.Yaml;

import java.io.IOException;
import java.util.List;
import play.Configuration;
import util.Env;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("----------------------------------------------------------------------------------------------------");
        Logger.info("Mailchimp API Key: \t " + Env.get("mailchimp.apikey"));
        Logger.info("Mailchimp List ID: \t " + Env.get("mailchimp.listid"));
        Logger.info("----------------------------------------------------------------------------------------------------");



        if (Hospital.find.findRowCount() == 0) {
            Ebean.save((List<Hospital>) Yaml.load("fixtures/test-data.yml"));
        }

        if (User.find.findRowCount() ==0) {

        }

    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

}