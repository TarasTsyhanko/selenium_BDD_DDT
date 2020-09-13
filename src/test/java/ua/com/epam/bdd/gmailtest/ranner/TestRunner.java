package ua.com.epam.bdd.gmailtest.ranner;

import com.google.inject.Inject;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;
import ua.com.epam.api.GmailClient;
import ua.com.epam.guice.ActionModule;
import ua.com.epam.guice.AsserterModule;
import ua.com.epam.guice.GmailApiModule;
import ua.com.epam.guice.PageModule;
import ua.com.epam.listener.GmailTestListeners;
import ua.com.epam.utils.FileManager;

import java.io.IOException;

@CucumberOptions(features = "src/test/resources/ua.com.epam.bdd.gmailtest/",
        glue = "ua.com.epam.bdd.gmailtest.step")
@Log4j2
@Guice(modules = {ActionModule.class, GmailApiModule.class, AsserterModule.class, PageModule.class})
@Listeners(GmailTestListeners.class)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Inject
    private GmailClient gmailClient;

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
