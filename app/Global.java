import com.avaje.ebean.Ebean;
import models.*;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.Play;
import play.libs.Yaml;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import util.Env;
import models.Address.State;
import models.Site.Kind;

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


        // ----------------------------
        Address a = new Address("Street", "123", "Baltimore", State.MD, "21212", 123, 123);
        Site site = new Site();
        site.address=a;
        site.kind= Kind.FREESTANDING;

        Group g1 = new Group();
        g1.name="Group";
        g1.isDemocratic=true;
        g1.isPartnershipOpportunity=true;


        site.group= g1;

        Ebean.save(site);




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