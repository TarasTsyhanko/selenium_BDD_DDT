package ua.com.epam.constant;

import ua.com.epam.config.ConfigProperties;

public class MessageConstant {
    public static final String SUCCESSFUL_DELETION_MESSAGE = String
            .format("%s ланцюжки повідомлень переміщено в кошик.", ConfigProperties.getSizeOfMarkMessages());
    public static final String SUCCESSFUL_MOVING_MESSAGE = String
            .format("%s ланцюжки повідомлень позначено як важливі.", ConfigProperties.getSizeOfMarkMessages());
    public static final String SUCCESSFUL_SENDING_MESSAGE = "Лист надіслано.";

    private MessageConstant() {
    }
}
