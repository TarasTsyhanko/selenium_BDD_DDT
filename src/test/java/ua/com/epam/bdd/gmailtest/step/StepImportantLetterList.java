package ua.com.epam.bdd.gmailtest.step;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import ua.com.epam.bdd.gmailtest.BaseTest;

import java.io.IOException;

import static ua.com.epam.constant.MessageConstant.SUCCESSFUL_DELETION_MESSAGE;
import static ua.com.epam.constant.MessageConstant.SUCCESSFUL_MOVING_MESSAGE;
import static ua.com.epam.constant.URLConstants.BASE_PAGE_URL;

@Log4j2
public class StepImportantLetterList extends BaseTest {
    @Before
    public void before() {
        setUp();
    }

    @After
    public void after() throws IOException {
        tearDown();
    }

    @When("Log in to Gmail account with {string} and {string}")
    public void logInToGmailAccountWithAnd(String login, String password) {
        loginAction.logInToGmailAccount(login, password);
    }

    @And("verify gmail main page is open")
    public void verifyGmailMainPageIsOpen() {
        Assert.assertTrue(loginAction.isBasePage(BASE_PAGE_URL));
    }

    @Then("mark {int} letter as important")
    public void markNLetterAsImportant(int n) {
        importantListAction.waitLetterToBeLoaded(n);
        importantListAction.moveNLettersToImportantList(n);
    }

    @And("Verify letter was moved to important list")
    public void verifyLetterWasMovedToImportantList() {
        Assert.assertTrue(importantListAction.isDisplayedMessage());
        letterAsserter.assertMessage(importantListAction.getMessageText(), SUCCESSFUL_MOVING_MESSAGE);
    }

    @Then("Open important letters list")
    public void openImportantLettersList() {
        importantListAction.openImportantLetterList();
    }

    @And("Mark {int} letters and delete it")
    public void markNLettersAndDeleteIt(int n) {
        importantListAction.markNImportantLettersAndDelete(n);

    }

    @And("Verify letter was deleted")
    public void verifyLetterWasDeleted() {
        Assert.assertTrue(importantListAction.isDisplayedMessage());
        letterAsserter.assertMessage(importantListAction.getMessageText(), SUCCESSFUL_DELETION_MESSAGE);
    }
}
