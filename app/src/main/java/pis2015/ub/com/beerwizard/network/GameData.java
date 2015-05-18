package pis2015.ub.com.beerwizard.network;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import pis2015.ub.com.beerwizard.R;

public class GameData extends Application {
    private static Handler spellsActivityHandler;
    private static String rule;
    private static User user;
    private static ConcurrentHashMap<String, UserInterface> users = new ConcurrentHashMap<>();

    /*
    We load the AllJoyn library
     */
    static {
        System.loadLibrary("alljoyn_java");
    }

    public GameData() {
    }

    public static Handler getSpellsActivityHandler() {
        return spellsActivityHandler;
    }

    public static void setSpellsActivityHandler(Handler spellsActivityHandler) {
        GameData.spellsActivityHandler = spellsActivityHandler;
    }

    public static User getUser() {
        return user;
    }

    public static UserInterface getUser(String uuid) {
        return users.get(uuid);
    }

    public static UserInterface getUser(int position) {
        return getUsers().get(position);
    }

    public static List<UserInterface> getUsers() {
        return new ArrayList<>(users.values());
    }

    public static ConcurrentHashMap<String, UserInterface> getUserDb() {
        return users;
    }

    public static String getRule() {
        return rule;
    }

    public static void setRule(String rule) {
        GameData.rule = rule;
    }

    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        user = new User(preferences.getString("name", "Change me!"), preferences.getInt("avatar", -1));
        rule = getString(R.string.rule);
    }
}