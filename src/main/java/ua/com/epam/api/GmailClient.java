package ua.com.epam.api;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ua.com.epam.api.service.GmailService;
import ua.com.epam.models.Letter;
import ua.com.epam.models.User;
import ua.com.epam.utils.FileManager;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Log4j2
public class GmailClient {
    @Inject
    private GmailService gmailService;

    @Step("create test letter")
    public synchronized void setTestLetters(List<Letter> letters, User recipient) {
        User user = FileManager.getUserAPI();
        gmailService.setGmailCredentials(user.getGmailCredentials(), user.getLogin());
        letters.forEach(letter -> {
            try {
                gmailService.sendMessage(recipient.getLogin(), letter.getSubject(), letter.getMessage());
            } catch (MessagingException | IOException e) {
                log.error(e.getStackTrace());
            }
        });
    }

    @Step("cleat gmail api")
    public synchronized void clearGmailApi(User user) throws IOException {
        gmailService.setGmailCredentials(user.getGmailCredentials(), user.getLogin());
        gmailService.deleteMessage(user.getLogin());
    }
}
