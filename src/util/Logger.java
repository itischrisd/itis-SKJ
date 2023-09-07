package util;

public class Logger {

    public static void log(String message) {
        System.out.println(message);
    }

    public static void logException(String message, Exception exception) {
        System.out.println(message);
        exception.printStackTrace();
    }
}
