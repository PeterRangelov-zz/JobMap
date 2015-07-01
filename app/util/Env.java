package util;

import play.Play;

/**
 * Created by peterrangelov on 6/30/15.
 */
public class Env {
    public static String get (String key) {
        return Play.application().configuration().getString(key);
    }
}
