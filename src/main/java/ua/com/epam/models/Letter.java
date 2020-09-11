package ua.com.epam.models;

import lombok.Data;

@Data
public class Letter {
    private String recipient;
    private String subject;
    private String message;
}
