package com.quantumretail.qi.keywords;

import com.quantumretail.qi.pagefactory.LoginFactory;
import com.quantumretail.selenium.GenericKeywords;
import com.quantumretail.utilities.PropertyLoader;
import org.openqa.selenium.support.PageFactory;

public class LoginKeywords {
    private static LoginFactory loginPage;
    GenericKeywords genericKeywords =new GenericKeywords();
    PropertyLoader propertyLoader =new PropertyLoader();

    public void loginQ(){
        String user=propertyLoader.getAppUserName();
        enterLoginCredentials(propertyLoader.getAppUserName(),propertyLoader.getAppPassword());
        login();
    }

    public void loginQ(String userName, String password){
        enterLoginCredentials(userName,password);
        login();
    }

    public void isDisplayedCheck() {
        loginPage.loginPageDiv.isDisplayed();
    }

    public void enterLoginCredentials(String username, String password) {
        //LOGGER.info("Logging in with username:" + username + " password:" + password);
        loginPage.usernameInput.clear();
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.clear();
        loginPage.passwordInput.sendKeys(password);
    }

    public void login() {
        loginPage.loginButton.click();
        //LOGGER.info("LoginKeywords submitted");
    }

    public void checkLoginErrors() {
        genericKeywords.waitForElement(loginPage.getErrorMessage());
    }

    public void logout() {
        try {
            genericKeywords.waitForElementClickAndSleep(loginPage.logoffLink(), 2000);
        } catch (Exception e) {
        }
    }

    public static void init() {
        loginPage = PageFactory.initElements(GenericKeywords.getDriver(), LoginFactory.class);
    }
}