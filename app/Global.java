import com.avaje.ebean.Ebean;
import models.Hospital;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.Play;
import play.libs.Yaml;

import java.util.List;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("----------------------------------------------------------------------------------------------------");
        Logger.info("Mailchimp API Key: \t " + System.getenv("MAILCHIMP_API_KEY"));
        Logger.info("Mailchimp List ID: \t " + System.getenv("MAILCHIMP_LIST_ID"));
        Logger.info("DB URL: \t " + System.getProperty("DB_URL"));

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