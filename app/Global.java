import com.avaje.ebean.Ebean;
import models.Hospital;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.Play;
import play.libs.Yaml;

import java.util.List;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Logger.info("APPLICATION HAS STARTED");
        Logger.info("DB URL -> "+Play.application().configuration().getString("DB_URL"));

        if (Hospital.find.findRowCount() == 0) {
            Ebean.save((List<Hospital>) Yaml.load("fixtures/test-data.yml"));
        }

    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

}