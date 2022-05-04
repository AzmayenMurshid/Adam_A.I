package CCT;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.speech.AudioException;
import javax.speech.EngineException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSON_manipulation {

    public static void checkJSON(Object key, Object value,
             Object initial, Object parent, String File){

        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(File)) {
            //Read json file
            Object obj = parser.parse(reader);
            JSONObject data = (JSONObject) obj;
            JSONObject target = (JSONObject) data.get(initial);
            if (target.toString().equals("{}") || obj.toString().equals("{}")) {
                writeJSON(key, value, parent, File);
            } else {
                appendJSON(key, value, initial, parent, File);
            }
        } catch (Exception e) {
            e.printStackTrace();
            readJSON(File);
            System.err.println("Datasets do not exist");
            try {
                App.textToSpeech("Datasets do not exist");
            } catch (EngineException | AudioException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void writeJSON(Object key, Object value, Object parent, String File) {
        JSONObject browser = new JSONObject();
        JSONObject objDetails = new JSONObject();
        objDetails.put(key, value);
        JSONArray arr = new JSONArray();
        arr.add(objDetails);
        JSONObject obj = new JSONObject();
        obj.put(parent, arr);
        browser.put("browser", obj);

        try (FileWriter file = new FileWriter(File)) {
            file.write(browser.toJSONString());
            file.flush();
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void readJSON(String File) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(File)) {
            Object obj = parser.parse(reader);

            JSONArray jsonArray =  new JSONArray();
            jsonArray.add(obj);

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                System.out.println(jsonObject);
            }
        } catch (IOException | ParseException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void appendJSON(Object key, Object value, Object initial, Object parent, String File)
            throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(File)) {
            //Read json file
            Object obj = parser.parse(reader);
            JSONObject oldData = (JSONObject) obj;
            JSONObject browser = (JSONObject) oldData.get(initial);
            JSONArray arr = (JSONArray) browser.get(parent);
            JSONObject newData = new JSONObject();
            newData.put(key, value);
            arr.add(newData);

            try (FileWriter file = new FileWriter(File)) {
                file.write(oldData.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void deleteJSON(Object key, Object value, Object initial, Object parent, String File)
            throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(File)) {
            //Read json file
            Object obj = parser.parse(reader);
            JSONObject oldData = (JSONObject) obj;
            JSONObject browser = (JSONObject) oldData.get(initial);
            JSONArray arr = (JSONArray) browser.get(parent);
            JSONObject newData = new JSONObject();
            newData.put(key, value);
            arr.remove(newData);

            try (FileWriter file = new FileWriter(File)) {
                file.write(oldData.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
