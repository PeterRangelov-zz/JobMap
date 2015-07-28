import com.avaje.ebean.Ebean;
import com.avaje.ebean.text.csv.CsvReader;
import models.Group;
import models.Site;
import models.User;
import org.apache.commons.codec.digest.DigestUtils;
import play.Application;
import play.GlobalSettings;
import play.Logger;

import java.io.File;
import java.io.FileReader;

import util.security.misc.Env;
import util.security.misc.Env.Variable;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Env.printEnvironmentVariables();
        String password = "123123";
        String hashedPassword = String.valueOf(DigestUtils.sha512Hex(password + "publicstatic"));

        Logger.debug("password: " + password);
        Logger.debug("hashed password: " + hashedPassword);

        Logger.debug(String.valueOf(hashedPassword.equals(DigestUtils.sha512Hex(password + "publicstatic"))));

//        if (Env.get(Variable.ENVIRONMENT).equalss("DEV")) {

            if (User.find.findList().isEmpty()) {
                try {
                    File file = new File("conf/fixtures/Fixtures - Users.csv");
                    FileReader reader = new FileReader(file);
                    CsvReader<User> csvReader = Ebean.createCsvReader(User.class);
                    csvReader.setHasHeader(true, false);
                    csvReader.setPersistBatchSize(20);

                    csvReader.addProperty("firstName");
                    csvReader.addProperty("lastName");
                    csvReader.addProperty("emailAddress");
                    csvReader.addIgnore();
                    csvReader.addProperty("accountLocked");
                    csvReader.addProperty("accountValidated");
                    csvReader.addProperty("role");
                    csvReader.addProperty("plan");
                    csvReader.addProperty("stripeToken");
                    csvReader.addProperty("passwordHash");
                    csvReader.addIgnore();

                    csvReader.process(reader);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (Site.find.findList().isEmpty()) {
                try {
                    File sitesFile = new File("conf/fixtures/Fixtures - Sites.csv");
                    FileReader sitesReader = new FileReader(sitesFile);
                    CsvReader<Site> sitesCsvReader = Ebean.createCsvReader(Site.class);
                    sitesCsvReader.setHasHeader(true, false);
                    sitesCsvReader.setPersistBatchSize(20);

                    sitesCsvReader.addProperty("name");
                    sitesCsvReader.addProperty("logoUrl");
                    sitesCsvReader.addProperty("type");
                    sitesCsvReader.addProperty("hasGroup");
                    sitesCsvReader.addProperty("isCommunity");
                    sitesCsvReader.addProperty("isAcademic");
                    sitesCsvReader.addProperty("isTrauma");
                    sitesCsvReader.addProperty("territory");
                    sitesCsvReader.addProperty("volume");
                    sitesCsvReader.addProperty("address.street");
                    sitesCsvReader.addProperty("address.suite");
                    sitesCsvReader.addProperty("address.city");
                    sitesCsvReader.addProperty("address.state");
                    sitesCsvReader.addProperty("address.zipcode");
                    sitesCsvReader.addProperty("address.lat");
                    sitesCsvReader.addProperty("address.lon");
                    sitesCsvReader.addIgnore();

                    File groupsFile = new File("conf/fixtures/Fixtures - Groups.csv");
                    FileReader groupsReader = new FileReader(groupsFile);
                    CsvReader<Group> groupsCsvReader = Ebean.createCsvReader(Group.class);
                    groupsCsvReader.setHasHeader(true, false);
                    groupsCsvReader.setPersistBatchSize(20);

                    groupsCsvReader.addProperty("name");
                    groupsCsvReader.addProperty("logoUrl");
                    groupsCsvReader.addProperty("type");
                    groupsCsvReader.addProperty("isDemocratic");
                    groupsCsvReader.addProperty("isPartnershipOpportunity");
                    groupsCsvReader.addIgnore();

                    sitesCsvReader.process(sitesReader);
                    groupsCsvReader.process(groupsReader);


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
//        }




    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
        if (Env.get(Variable.ENVIRONMENT).equals("DEV")) {
        }
    }
}


