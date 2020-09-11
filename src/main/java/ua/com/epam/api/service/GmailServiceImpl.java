package ua.com.epam.api.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.Thread;
import lombok.extern.log4j.Log4j2;
import ua.com.epam.models.GmailCredentials;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Log4j2
public class GmailServiceImpl implements GmailService {
    private static final String APPLICATION_NAME = "Mail";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final HttpTransport httpTransport;
    private GmailCredentials gmailCredentials;
    private String email;

    public GmailServiceImpl(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    @Override
    public synchronized void setGmailCredentials(GmailCredentials gmailCredentials, String email) {
        this.gmailCredentials = gmailCredentials;
        this.email = email;
    }

    @Override
    public boolean sendMessage(String recipient, String subject, String body) throws MessagingException,
            IOException {
        log.info(" send email[" + recipient + " : " + subject + " : " + body + "]");
        Message message = createMessageWithEmail(
                createEmail(recipient, email, subject, body));

        return createGmail().users()
                .messages()
                .send(email, message)
                .execute()
                .getLabelIds().contains("SENT");
    }

    private Gmail createGmail() {
        log.info("create Gmail API connection");
        Credential credential = authorize();
        return new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public synchronized void deleteMessage(String email) throws IOException {
        log.info(" clear gmail user's : " + email);
        Gmail gmail = createGmail();
        ListThreadsResponse threadsResponse = gmail.users().threads().list(email).execute();
        if (threadsResponse.getThreads() != null) {
            List<Thread> threads = threadsResponse.getThreads();
            threads.forEach(thread -> {
                try {
                    gmail.users().threads().delete(email, thread.getId()).execute();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            });
        }
    }

    private MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException {
        MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    private Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);

        return new Message()
                .setRaw(Base64.encodeBase64URLSafeString(buffer.toByteArray()));
    }

    private synchronized Credential authorize() {
        return new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(JSON_FACTORY)
                .setClientSecrets(gmailCredentials.getClientId(), gmailCredentials.getClientSecret())
                .build()
                .setAccessToken(gmailCredentials.getAccessToken())
                .setRefreshToken(gmailCredentials.getRefreshToken());
    }
}
