package org.selftravel.app;

import android.app.Application;
import android.os.Environment;

import org.selftravel.beans.UserInfo;
import org.xutils.DbManager;
import org.xutils.x;

public class SelfTravelApp extends Application {
    private static boolean isLoggedIn = false;
    public static DbManager.DaoConfig daoConfig;

    public static UserInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        SelfTravelApp.userInfo = userInfo;
    }

    private static UserInfo userInfo;

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static boolean LogIn(UserInfo userInfo) {
        if (userInfo == null) {
            return false;
        }
        SelfTravelApp.userInfo = userInfo;
        isLoggedIn = true;
        return true;
    }

    public static void logOut() {
        userInfo = null;
        isLoggedIn = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        x.Ext.init(this);
        x.Ext.setDebug(true);
        daoConfig = new DbManager.DaoConfig()
                .setAllowTransaction(true)
                .setDbVersion(1)
                .setDbName("selfTravel.db");
    }
}
