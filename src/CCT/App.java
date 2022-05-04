package CCT;

import java.awt.*;
import javax.speech.AudioException;

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Synthesizer;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
import java.util.*;

public class App extends JSON_manipulation{

    private static final String JSON_FILE = "dataComponents.json";
    private static final String BROWSER_INITIAL = "browser";
    private static final String BROWSER_PARENT = "websites";

    private static final String[][] extensions = {
            {"html", "htm", "shtml", "xhtml", "xht", "pdf", "php", "asp", "aspx", "jsp", "cfm", "cfml",
                "pl", "rb", "rhtml", "txt", "app"},

            {"org", "com", "net", "edu", "gov", "mil", "int", "biz", "coop", "app", "info"}
    };

    public static void main(String[] args){
        try {
            Browser.Search();
            //webBrowser();
            //deleteJSON("github", "www.github.com", BROWSER_INITIAL, BROWSER_PARENT, JSON_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void textToSpeech(String txt) throws EngineException, AudioException, InterruptedException {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
        Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
        Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
        synthesizer.allocate();
        synthesizer.resume();
        synthesizer.speakPlainText(txt, null);
        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        synthesizer.deallocate();
    }

    public static void webBrowser() throws IOException, AudioException, EngineException, InterruptedException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the website: ");
        String url = scanner.nextLine();
        String[] urlArray = url.split("\\.");
        String extension = urlArray[urlArray.length - 1];

        textToSpeech(String.format("Opening %s", url));
        if (!extension.equals("com") && !extension.equals("org")
                && !extension.equals("net") && !extension.equals("edu")) {
            for (String ext : extensions[0]) {
                if (extension.equals(ext)) {
                    Runtime.getRuntime().exec("cmd /c start " + url);
                }
            }
        } else{
            for (String ext : extensions[1]) {
                if (extension.equals(ext)) {
                    Desktop.getDesktop().browse(URI.create(String.format("http://www.%s", url)));
                }
            }
        }
        checkJSON(urlArray[0], String.format("www.%s", url), BROWSER_INITIAL, BROWSER_PARENT, JSON_FILE);
    }
}
