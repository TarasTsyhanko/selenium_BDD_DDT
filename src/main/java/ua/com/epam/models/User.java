package ua.com.epam.models;

public class User {
    private  String fullName;
    private  String login;
    private  String password;
    private  GmailCredentials gmailCredentials;

    public User(String fullName, String login, String password, GmailCredentials gmailCredentials) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.gmailCredentials = gmailCredentials;
    }

    public User() {
    }

    public String getFullName() {
        return fullName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public GmailCredentials getGmailCredentials() {
        return gmailCredentials;
    }

    @Override
    public String toString() {
        return "User{" + fullName + '\'' +
                '}';
    }
}
