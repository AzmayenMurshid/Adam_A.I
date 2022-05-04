package CCT;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class OS extends JSON_manipulation {
    private static final String JSON_FILE = "dataComponents.json";
    private static final String OS = "OS";
    private static final String OS_PARENT = "windows";

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    static LocalDateTime now = LocalDateTime.now();

    @SuppressWarnings("unused")
    public static void shutdown() {

        Runtime runtime = Runtime.getRuntime();
        try {
            checkJSON(dtf.format(now), "shut down os", OS, OS_PARENT, JSON_FILE);
            App.textToSpeech("shutting down...");
            //runtime.exec("shutdown -s -t 0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static void logoff() {

        Runtime runtime = Runtime.getRuntime();
        try {
            checkJSON(dtf.format(now), "log off os", OS, OS_PARENT, JSON_FILE);
            App.textToSpeech("logging off...");
            //runtime.exec("shutdown -l -t 0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static void hibernate() {

        Runtime runtime = Runtime.getRuntime();
        try {
            checkJSON(dtf.format(now), "hibernate os", OS, OS_PARENT, JSON_FILE);
            App.textToSpeech("hibernating...");
            //runtime.exec("shutdown -h -t 0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static void lock() {

        Runtime runtime = Runtime.getRuntime();
        try {
            checkJSON(dtf.format(now), "lock os", OS, OS_PARENT, JSON_FILE);
            App.textToSpeech("locking...");
            runtime.exec("shutdown -l -t 0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static void restart() {
        try {
            checkJSON(dtf.format(now), "restart os",OS, OS_PARENT, JSON_FILE);
            App.textToSpeech("restarting...");
            //runtime.exec("shutdown -r -t 0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
