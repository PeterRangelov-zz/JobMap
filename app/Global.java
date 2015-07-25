import com.avaje.ebean.Ebean;
import models.ApplicationEntry;
import models.Site;
import models.User;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Yaml;

import java.util.*;

import scala.App;
import util.Env;
import util.Env.Variable;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Env.printEnvironmentVariables();

        if (Env.get(Variable.ENVIRONMENT).equals("DEV")) {



//            Startup.generateMainFixtureFile("groups", "sites");

            if (Site.find.findList().isEmpty()){
                Map<String, List<Object>> all = ((Map<String, List<Object>>) Yaml.load("fixtures/test/sites.yml"));
//                Ebean.save(all.get("recruiters"));
//            Ebean.save(all.get("users"));

                Ebean.save(all.get("sites"));
//                Ebean.save((List<Site>) Yaml.load("fixtures/test/sites.yml"));
            }


        }



    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
        if (Env.get(Variable.ENVIRONMENT).equals("DEV")) {
        }
    }
}


