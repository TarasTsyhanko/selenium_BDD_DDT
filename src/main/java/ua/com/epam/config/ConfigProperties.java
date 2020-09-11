package ua.com.epam.config;

public class ConfigProperties {
    public static String getBaseUrl() {
        return PropertyFile.getProperty("base_url");
    }

    public static String getBrowser() {
        return PropertyFile.getProperty("browser");
    }

    public static String getChromeDriverPath() {
        return PropertyFile.getProperty("chrome_driver_path");
    }

    public static String getFirefoxDriverPath() {
        return PropertyFile.getProperty("firefox_driver_path");
    }

    public static String getEdgeDriverPath() {
        return PropertyFile.getProperty("edge_driver_path");
    }


    public static String getChromeDriver() {
        return PropertyFile.getProperty("chrome_driver");
    }

    public static String getFirefoxDriver() {
        return PropertyFile.getProperty("firefox_driver");
    }

    public static String getEdgeDriver() {
        return PropertyFile.getProperty("edge_driver");
    }

    public static String getUsersFilePath() {
        return PropertyFile.getProperty("users_file_path");
    }

    public static String getLogsFilePath() {
        return PropertyFile.getProperty("logs_file_path");
    }

    public static String getLetterFilePath() {
        return PropertyFile.getProperty("letter_file_path");
    }

    public static String getUserApiFilePath() {
        return PropertyFile.getProperty("user_api_file_path");
    }

    public static String getUserCsvFilePath() {
        return PropertyFile.getProperty("users_csv_file_path");
    }

    public static int getSizeOfMarkMessages() {
        return Integer.parseInt(PropertyFile.getProperty("size_of_mark_messages"));
    }

}
