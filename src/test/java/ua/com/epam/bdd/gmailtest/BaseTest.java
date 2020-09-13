package ua.com.epam.bdd.gmailtest;

import com.google.inject.Inject;
import org.testng.annotations.Guice;
import ua.com.epam.asserters.ImportantLetterAsserter;
import ua.com.epam.factory.DriverContainer;
import ua.com.epam.guice.ActionModule;
import ua.com.epam.guice.AsserterModule;
import ua.com.epam.guice.GmailApiModule;
import ua.com.epam.guice.PageModule;
import ua.com.epam.ui.actions.ImportantLettersAction;
import ua.com.epam.ui.actions.LoginAction;
import ua.com.epam.utils.allure.AllureAttachment;
import ua.com.epam.config.ConfigProperties;

import java.io.IOException;


public class BaseTest {
    @Inject
    protected ImportantLettersAction importantListAction;
    @Inject
    protected ImportantLetterAsserter letterAsserter;
    @Inject
    protected LoginAction loginAction;

    public void setUp() {
        DriverContainer.getDriver().get(ConfigProperties.getBaseUrl());
    }

    public void tearDown() throws IOException {
        DriverContainer.quitDriver();
        AllureAttachment.addFileToAllure(ConfigProperties.getLogsFilePath());
    }
}
