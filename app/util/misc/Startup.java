package util.misc;

import com.avaje.ebean.Ebean;
import org.apache.commons.io.FileUtils;
import play.Logger;
import play.libs.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by peterrangelov on 6/30/15.
 */
public class Startup {
    public static void generateMainFixtureFile(String... args) {

        System.out.println("Thread Running");
        try {
            FileUtils.forceDelete(new File("conf/fixtures/all.yml"));
            File mainFile = new File("conf/fixtures/all.yml");

            for (String arg : args) {
                Logger.info("Writing to all.yml: " + arg);

                File file = new File("conf/fixtures/" + arg + ".yml");
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();
                FileUtils.write(mainFile, new String(data), true);
            }
        } catch (FileNotFoundException e) {
            Logger.info("FILE NOT FOUND");
        } catch (IOException e) {
            Logger.info("IO Exception");
        }
    }


    public static void seedFixturesFromMain(String... args) {
        Thread thread = new Thread() {
            public void run() {
                Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("fixtures/main.yml");

                for (String arg : args) {
                    Logger.info("Saving fixtures from main: " + args);
                    Ebean.save(all.get(arg));
                }
            }


        };
    }


}
