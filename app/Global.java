import com.avaje.ebean.Ebean;
import models.Site;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.Yaml;

import java.util.*;

import util.Env;
import util.Env.Variable;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Env.printEnvironmentVariables();

        if (Env.get(Variable.ENVIRONMENT).equals("DEV")) {


//            Startup.generateMainFixtureFile("groups", "sites");

            if (Site.find.findList().isEmpty()){
                Map<String, List<Object>> all = ((Map<String, List<Object>>) Yaml.load("fixtures/test/all.yml"));
                Ebean.save(all.get("recruiters"));
//            Ebean.save(all.get("users"));

                Ebean.save(all.get("sites"));
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