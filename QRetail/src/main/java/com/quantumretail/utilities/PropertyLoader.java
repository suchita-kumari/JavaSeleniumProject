package com.quantumretail.utilities;

import java.util.ResourceBundle;

/**
 * Created by Suchita on 12-06-2017.
 */
public class PropertyLoader {
    //this is the property loader
    private final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config");

    public String getBrowserType() {
        return RESOURCE_BUNDLE.getString("browserType");
    }

    public String getAppUserName() {
        return RESOURCE_BUNDLE.getString("username");
    }

    public String getAppPassword() {
        return RESOURCE_BUNDLE.getString("password");
    }

    public String getAppUrl() {
        return RESOURCE_BUNDLE.getString("server.url");
    }

    public  String getDbConnectionUrl() {
        return RESOURCE_BUNDLE.getString("dbConnectionUrl");
    }

    public String getDbUserName() {
        return RESOURCE_BUNDLE.getString("dbUserName");
    }

    public String getDbPassword() {
        return RESOURCE_BUNDLE.getString("dbPassword");
    }

}
