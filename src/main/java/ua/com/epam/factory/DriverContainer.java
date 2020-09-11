package ua.com.epam.factory;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import ua.com.epam.config.ConfigProperties;

@Log4j2
public class DriverContainer {

    private static final ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (DRIVER_POOL.get() == null) {
            DRIVER_POOL.set(DriverFactory.createDriver(ConfigProperties.getBrowser()));
        }
        return DRIVER_POOL.get();
    }

    public static void quitDriver() {
        log.info("try to quit driver");
        if (DRIVER_POOL.get() != null) {
            DRIVER_POOL.get().quit();
            DRIVER_POOL.set(null);
        }
        log.info("driver was successfully closed");
    }

    private DriverContainer() {

    }
}
