package ua.com.epam.constant;

public class ScriptConstants {
    public static final String SCRIPT_CLICK = "arguments[0].click();";
    public static final String SCRIPT_SCROLL_TO_ELEMENT = "arguments[0].scrollIntoView(true);";
    public static final String SCRIPT_READY_STATE = "return document.readyState";
    public static final String COMPLETE = "complete";

    private ScriptConstants() {
    }
}
