package ua.com.epam.factory;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.com.epam.config.ConfigProperties;

import java.util.concurrent.TimeUnit;

@Log4j2
public class DriverFactory {
    public static final int IMPLICITLY_TIME = 20;

    protected static WebDriver createDriver(String browser) {
        WebDriver driver;
        log.info("try to create driver " + browser);
        if (browser.equals("firefox")) {
            System.setProperty(ConfigProperties.getFirefoxDriver(), ConfigProperties.getFirefoxDriverPath());
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            System.setProperty(ConfigProperties.getEdgeDriver(), ConfigProperties.getEdgeDriverPath());
            driver = new EdgeDriver();
        } else {
            System.setProperty(ConfigProperties.getChromeDriver(), ConfigProperties.getChromeDriverPath());
            driver = new ChromeDriver();
        }
        setWait(driver, IMPLICITLY_TIME);
        driver.manage().window().maximize();
        log.info(browser + " driver was successfully created");
        return driver;
    }

    public static void setWait(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    private DriverFactory() {

    }
}
