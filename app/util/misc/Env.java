package util.misc;

import play.Logger;
import play.Play;

public class Env {
    public static String get (Variable key) {
        return Play.application().configuration().getString(key.confName);
    }

    public static void printEnvironmentVariables () {
        Logger.info("------------------------------------------------------------------");
        Logger.info("ENVIRONMENT VARIABLES FOLLOW:");
        for (Variable variable : Variable.values()) {
            Logger.info(String.format("%-23s %-25s %s", variable, variable.confName, Env.get(variable)));
        }
        Logger.info("------------------------------------------------------------------");
    }

    public enum Variable {
        ENVIRONMENT("environment"),
        MAILCHIMP_API_KEY("mailchimp.apikey"),
        MAILCHIMP_LIST_ID("mailchimp.listid"),
        SENDGRID_USERNAME("sendgrid.username"),
        SENDGRID_PASSWORD("sendgrid.password"),
        DB_EVOLUTIONS_PLUGIN("applyEvolutions.default"),
        ENABLE_EVOLUTIONS("applyEvolutions.default"),
        DB_APPLY_EVOLUTIONS("applyEvolutions.default"),
        EVOLUTION_PLUGIN("evolutionplugin"),
        DB_URL("db.default.url"),
//        DB_NAME(),
        DB_PASSWORD("db.default.password"),
        STRIPE_API_KEY("123"),
        HOST("host"),
        PWD_SALT("pwd.salt");


        private String confName;

        Variable (String confName) { this.confName=confName; }

        public String getConfName() { return confName; }
    }
}