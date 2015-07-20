import com.avaje.ebean.Ebean;
import models.*;
import org.apache.commons.lang3.StringUtils;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.Play;
import play.libs.Yaml;

import java.io.*;
import java.util.*;

import util.Env;
import util.Env.Variable;
import models.Address.State;
import models.Site.Kind;
import org.apache.commons.io.FileUtils;
import util.Startup;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Env.printEnvironmentVariables();
//        Startup.cleanDB();

        if (Env.get(Variable.ENVIRONMENT).equals("DEV")) {



            File mainFile = new File("conf/fixtures/main.yml");


            try {
                File file = new File("conf/fixtures/groups.yml");
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();
                FileUtils.write(mainFile, new String(data), false);

                File file2 = new File("conf/fixtures/sites.yml");
                FileInputStream fis2 = new FileInputStream(file2);
                byte[] data2 = new byte[(int) file2.length()];
                fis2.read(data2);
                fis2.close();
                FileUtils.write(mainFile, new String(data2), true);

                Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("fixtures/main.yml");
                Ebean.save(all.get("sites"));
                Ebean.save(all.get("groups"));

            } catch (FileNotFoundException e) {
                Logger.info("FILE NOT FOUND");
            } catch (IOException e) {
                Logger.info("IO Exception");
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