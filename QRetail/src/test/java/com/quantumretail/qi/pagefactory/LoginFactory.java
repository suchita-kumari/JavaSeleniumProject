package com.quantumretail.qi.pagefactory;

import com.quantumretail.selenium.GenericKeywords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginFactory {
    public static final String LOGOUT_LINK_ID = "logout";
    GenericKeywords genericKeywords = new GenericKeywords();
    @FindBy(how = How.ID, using = "logon")
    public WebElement loginPageDiv;

    @FindBy(how = How.ID, using = "j_id_username")
    public WebElement usernameInput;

    @FindBy(how = How.ID, using = "j_id_password")
    public WebElement passwordInput;

    @FindBy(how = How.ID, using = "id_submitLogin")
    public WebElement loginButton;

    @FindBy(how = How.ID, using = LOGOUT_LINK_ID)
    public WebElement logoutLink;


    public WebElement getErrorMessage() {
        WebElement element = loginPageDiv.findElement(By.cssSelector("div.error"));
        return element;
    }

    public WebElement logoffLink() {
        return genericKeywords.waitForElement(logoutLink);
    }
}