package util;

import play.Logger;
import play.Play;

public class Env {
    public static String get (Variable key) {
        return Play.application().configuration().getString(key.toString());
    }

    public static void printEnvironmentVariables () {
        Logger.info("------------------------------------------------------------------");
        Logger.info("ENVIRONMENT VARIABLES FOLLOW:");
        for (Env.Variable variable : Env.Variable.values()) {
            Logger.info(String.format("%-30s %s", variable, Env.get(variable)));
        }
        Logger.info("------------------------------------------------------------------");
    }

    public enum Variable {
        ENVIRONMENT, MAILCHIMP_API_KEY, MAILCHIMP_LIST_ID, DB_EVOLUTIONS_PLUGIN, ENABLE_EVOLUTIONS, DB_APPLY_EVOLUTIONS_DEFAULT, DB_URL, DB_NAME, DB_PASSWORD, STRIPE_API_KEY
    }
}