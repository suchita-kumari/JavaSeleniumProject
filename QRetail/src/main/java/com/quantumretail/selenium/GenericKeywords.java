package com.quantumretail.selenium;

import com.google.common.base.Function;
import com.quantumretail.utilities.PropertyLoader;
import com.quantumretail.utilities.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Suchita on 31-05-2017.
 */
public class GenericKeywords extends TestBase {
    private static WebDriver driver;
    private final Integer MAX_Default_Timeout_Seconds = 60;
    private final Integer MIN_Default_Timeout_Seconds = 30;
    PropertyLoader propertyLoader = new PropertyLoader();

    public synchronized static WebDriver getDriver() {
        return driver;
    }

    public synchronized void openBrowser() {
        driver = getBrowserDriver(propertyLoader.getBrowserType());
    }

    public void openApplicationUrl(String url) {
        driver.get(url);
    }

    public void typeIn(WebElement txtBoxElemnt, String txt) {
        clearTextBox(txtBoxElemnt);
        txtBoxElemnt.sendKeys(txt);
    }

    public void clearTextBox(WebElement textBoxElemnt) {
        textBoxElemnt.clear();
    }

    public void clickOn(WebElement buttonElemnt) {
        waitForElementToBeClickable(buttonElemnt);
        buttonElemnt.click();
    }

    public void clickWithJS(WebElement buttonElemnt){
        JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
        executor.executeScript("arguments[0].click();", buttonElemnt);
    }

    public void closeBrowser() {
        driver.close();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void dismissAlert() {
//        waitForAlertToPresent();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public WebElement waitForElement(By selector) {
        return waitForElement(driver.findElement(selector));
    }

    public WebElement waitForElement(By selector, Integer waitTimeInSeconds) {
        return waitForElement(driver.findElement(selector), waitTimeInSeconds);
    }

    public WebElement waitForElement(WebElement elementToWaitFor) {
        return waitForElement(elementToWaitFor, null);
    }

    public WebElement fluentWait(final By locator) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement foo = wait.until(
                new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(locator);
                    }
                }
        );
        return foo;
    }

    public WebElement waitForElement(WebElement elementToWaitFor, Integer waitTimeInSeconds) {
        if (waitTimeInSeconds == null) {
            waitTimeInSeconds = MIN_Default_Timeout_Seconds;
        }
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
    }

    public WebElement waitForElementToBeClickable(WebElement elementToWaitFor) {
        WebDriverWait wait = new WebDriverWait(driver, MIN_Default_Timeout_Seconds);
        return wait.until(ExpectedConditions.elementToBeClickable(elementToWaitFor));
    }

    public void waitForElementClickAndSleep(WebElement elementToWaitFor, long mills) throws Exception {
        WebElement we = waitForElement(elementToWaitFor, null);
        we.click();
        Thread.sleep(mills);
    }

    public void waittextToBePresentInElementLocated(WebElement element, String text, int TimeInMillSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, TimeInMillSeconds);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitForAlertToPresent() {
        WebDriverWait wait = new WebDriverWait(driver, MIN_Default_Timeout_Seconds);
        wait.ignoring(NoAlertPresentException.class).until(ExpectedConditions.alertIsPresent());
    }


    public void waitForPageLoad() {
        Wait<WebDriver> wait = new WebDriverWait(GenericKeywords.getDriver(), 30);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State       : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }


    public void performKeysAction(WebElement element, String keysAction) throws Throwable {
        String[] keysActions = keysAction.split(", ");
        JavascriptExecutor js = (JavascriptExecutor) GenericKeywords.getDriver();
        js.executeScript("arguments[0].focus();", element);
        for (String action : keysActions) {
            if (action.contains("enter text:")) {
                String[] text = action.split("text:");
                element.sendKeys(text[1].trim());
            } else if (action.equalsIgnoreCase("click")) {
                element.click();
            } else if (action.equalsIgnoreCase("clear")) {
                element.clear();
            } else if (action.equalsIgnoreCase("delete")) {
                element.sendKeys(Keys.DELETE);
            } else if (action.equalsIgnoreCase("backspace")) {
                element.sendKeys(Keys.BACK_SPACE);
            } else if (action.contains("backspace *")) {
                int count = Integer.parseInt(action.split("\\*")[1].trim());
                for (int i = 0; i < count; i++) {
                    element.sendKeys(Keys.BACK_SPACE);
                }
            } else if (action.equalsIgnoreCase("left arrow")) {
                element.sendKeys(Keys.ARROW_LEFT);
            } else if (action.equalsIgnoreCase("right arrow")) {
                element.sendKeys(Keys.ARROW_RIGHT);
            } else if (action.equalsIgnoreCase("tab")) {
                element.sendKeys(Keys.TAB);
            } else if (action.equalsIgnoreCase("enter")) {
                element.sendKeys(Keys.ENTER);
            } else if (action.equalsIgnoreCase("return")) {
                element.sendKeys(Keys.RETURN);
            } else if (action.equalsIgnoreCase("space")) {
                element.sendKeys(Keys.SPACE);
            }
        }
    }

    private WebDriver getBrowserDriver(String browserType) {
        try {
            File file;
            DesiredCapabilities capabilities;
            switch (browserType) {
                case "chrome":
                    file = new File("Drivers" + File.separator + "chromedriver.exe");
                    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("disable-infobars");
                    driver = new ChromeDriver(options);
                   // driver = new ChromeDriver(capabilities);
                    break;
                case "IE":
                    file = new File("Drivers" + File.separator + "IEDriverServer.exe");
                    System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                    capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setCapability("ignoreZoomSetting", true);
                    capabilities.setCapability("Enable Protected Mode", true);
                    driver = new InternetExplorerDriver(capabilities);
                    break;
                case "firefox":
                    file = new File("Drivers" + File.separator + "geckodriver.exe");
                    System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
                    capabilities = DesiredCapabilities.firefox();
                    capabilities.setCapability("marionette", true);
                    FirefoxProfile fp = getFirefoxProfile();
                    //fp.setEnableNativeEvents(true);
                    fp.setPreference("focusmanager.testmode", true);
                    capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
                    capabilities.setCapability(FirefoxDriver.PROFILE, fp);
                    driver = new FirefoxDriver(capabilities);
                    break;
                default:
                    capabilities = DesiredCapabilities.phantomjs();
                    capabilities.setJavascriptEnabled(true);
                    capabilities.setCapability("takesScreenshot", true);
            }
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addAllBrowserSetup(driver);
        return driver;
    }

    private FirefoxProfile getFirefoxProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", "/tmp");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv");
        return profile;
    }

    private void addAllBrowserSetup(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        org.openqa.selenium.Dimension dim = new org.openqa.selenium.Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(dim);
    }
}
