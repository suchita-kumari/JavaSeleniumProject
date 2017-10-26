package com.quantumretail.qi.pagefactory;

/**
 * Created by Bhagya on 14-06-2017.
 */
public enum QuantumScreens {
    HQ("HqFactory", "product-hq-title", "product/hq.jsp"),
    ProductSetup("Product Setup", "product-setup-title", "mvc/product/setup/main"),
    ProductReview("Product Review", "product-review-title", "product/review.jsp"),
    Distribution("Distribution", "distribution-setup-title", "mvc/distribution/setup/main"),
    Forecast("Forecast", "forecast-setup-title", "mvc/forecast/setup/main"),
    OrderPlan("Order Plan", "orderplan-setup-title","mvc/orderplan/setup/main"),
    Events("Events", "event-setup-title", "mvc/event/main"),
    Administration("Administration", "admin-title","mvc/user/admin"),

    Users("Users", "user-title", "mvc/user/admin"),
    SeasonalOverrides("Seasonal Overrides", "seasonal-override-title", "seasonaloverride/seasonaloverride.jsp"),
    AlertTolerances("Alert Tolerances", "tolerance-admin-title", "mvc/tolerance/main");

    String tabName;
    String id;
    String url;

    private QuantumScreens(String name, String aId, String url){
        this.tabName = name;
        this.id = aId;
        this.url = url;
    }

    @Override
    public String toString() {
        return tabName;
    }

    public String getId(){
        return id;
    }

    public String getUrl(){
        return url;
    }

    public static QuantumScreens getEnum(String value) {
        if(value == null)
            throw new IllegalArgumentException();
        for(QuantumScreens v : values())
            if(value.equalsIgnoreCase(v.tabName)) return v;
        throw new IllegalArgumentException();
    }
}