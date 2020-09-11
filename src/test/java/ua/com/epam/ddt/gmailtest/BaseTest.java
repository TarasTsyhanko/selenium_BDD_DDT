package ua.com.epam.ddt.gmailtest;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.guice.GmailApiModule;
import ua.com.epam.api.GmailClient;
import ua.com.epam.listener.GmailTestListeners;
import ua.com.epam.guice.ActionModule;
import ua.com.epam.guice.AsserterModule;
import ua.com.epam.guice.PageModule;
import ua.com.epam.utils.allure.AllureAttachment;
import ua.com.epam.config.ConfigProperties;
import ua.com.epam.models.User;
import ua.com.epam.utils.FileManager;

import java.io.IOException;

@Log4j2
@Guice(modules = {PageModule.class, ActionModule.class, AsserterModule.class, GmailApiModule.class})
@Listeners(GmailTestListeners.class)
public class BaseTest {
    @Inject
    protected GmailClient gmailClient;

    @BeforeMethod
    public void setUp() {
        DriverContainer.getDriver().get(ConfigProperties.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        DriverContainer.quitDriver();
        AllureAttachment.addFileToAllure(ConfigProperties.getLogsFilePath());
    }

    @BeforeSuite
    public void createLettersAPI() {
        FileManager.getUsers().forEach(user -> {
            gmailClient.setTestLetters(FileManager.getLetters(), user);
        });
    }

    @AfterSuite
    public void clearGmailApi() {
        FileManager.getUsers().forEach(user -> {
            try {
                gmailClient.clearGmailApi(user);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }
}
