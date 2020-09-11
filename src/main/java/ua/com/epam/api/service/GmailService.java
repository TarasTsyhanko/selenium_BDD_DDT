package ua.com.epam.api.service;

import ua.com.epam.models.GmailCredentials;

import javax.mail.MessagingException;
import java.io.IOException;

public interface GmailService {
    void setGmailCredentials(GmailCredentials gmailCredentials, String email);

    boolean sendMessage(String recipient, String subject, String body) throws MessagingException, IOException;

    void deleteMessage( String email) throws IOException;
}
