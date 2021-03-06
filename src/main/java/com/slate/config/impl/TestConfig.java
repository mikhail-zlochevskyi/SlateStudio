package com.slate.config.impl;

import com.slate.config.Appium;
import com.slate.config.User;

import static org.aeonbits.owner.ConfigFactory.create;

public class TestConfig {

    public static String getUserLogin() {
        User testUser = create(User.class);
        return testUser.userLogin();
    }

    public static String getUserPass() {
        User testUser = create(User.class);
        return testUser.userPass();
    }

    public static String getUserToken() {
        User testUser = create(User.class);
        return testUser.userToken();
    }

    public static String getAppiumServer() {
        Appium appium = create(Appium.class);
        return appium.appiumServer();
    }
}
