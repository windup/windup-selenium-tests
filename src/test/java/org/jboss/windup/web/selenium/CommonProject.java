package org.jboss.windup.web.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class CommonProject {

    public static final String RHAMT_BASE_URL_PROPERTY = "baseUrl";
    public static final String WITH_LOGIN_PROPERTY = "withLogin";
    public static final String HEADLESS_PROPERTY = "headless";
    private static final String RHAMT_BASE_URL_DEFAULT = "http://127.0.0.1:8080/";
    protected WebDriver driver;
    private final String rhamtBaseUrl;
    private final boolean withLogin;
    private final boolean headless;

    public CommonProject()
    {
        rhamtBaseUrl = System.getProperty(RHAMT_BASE_URL_PROPERTY, RHAMT_BASE_URL_DEFAULT);
        withLogin = System.getProperty(WITH_LOGIN_PROPERTY) != null;
        headless = Boolean.parseBoolean(System.getProperty(HEADLESS_PROPERTY, "true"));

        // Create a new instance of the driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--networkConnectionEnabled");
        driver = new ChromeDriver(options);

        // opens up the browser
        driver.get(rhamtBaseUrl);
        if (withLogin)
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("kc-logo-wrapper")));
            login();
        }
    }

    public String getRhamtBaseUrl() {
        return rhamtBaseUrl;
    }

    public void login()
    {
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("rhamt");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("password");
        WebElement loginButton = driver.findElement(By.id("kc-login"));
        loginButton.click();
    }

    public void waitForProjectList()
    {
        WebElement header = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("projects-list")));
    }

    public void waitForNoProjectWelcomePage()
    {
        WebElement header = (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("wu-no-projects-welcome")));
    }

    public void waitForProjectLoad()
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".activated-item")));
    }

    /**
     * from the project list screen this will navigate to whichever project is given by the name
     * @param projName the exact string form of the project name
     * @return true if the project is found
     */
    public boolean navigateProject(String projName) {
        int x = 1;
        while (true) {
            try {
                WebElement proj = driver
                        .findElement(By.xpath("(//*[@class='list-group-item  project-info  tile-click'])[" + x + "]"));

                WebElement title = proj.findElement(By.cssSelector(
                        "div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
                if (title.getText().equals(projName)) {
                    title.click();
                    return true;
                }
                x++;
                continue;
            } catch (NoSuchElementException e) {
                break;
            }
        }
        return false;
    }


    /**
     * closes the browser
     */
    public void closeDriver() {
        driver.quit();
    }
}
