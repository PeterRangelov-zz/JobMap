import com.avaje.ebean.Ebean;
import models.Address;
import models.Applicant;
import models.CV;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.Play;
import play.libs.Yaml;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import play.Configuration;
import util.Env;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        Env.printEnvironmentVariables();

        Applicant me = new Applicant();
        CV mine = new CV();
        mine.firstName="peter";
        mine.lastName="rangelov";
        mine.email="dfs";
        mine.medSchool="JHU";
        mine.medSchoolGraduation=new Date();
        mine.residencyProgram="JHU";
        mine.residencyGraduation=new Date();
        mine.state= Address.State.AK;

        me.cv = mine;

        me.save();

        List<Applicant> applicants = Ebean.find(Applicant.class).findList();

        Logger.info(applicants.get(0).toString());

//        if (Hospital.find.findRowCount() == 0) {
//            Ebean.save((List<Hospital>) Yaml.load("fixtures/test-data.yml"));
//        }
//
//        if (User.find.findRowCount() ==0) {
//
//        }

    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

}