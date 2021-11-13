package MonopolyJunior;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.json.simple.*;
import org.json.simple.parser.*;


public class Language {
    private static String language;
    private static final Language instance = new Language();
    private static JSONObject list;

    private Language() {
        language = System.getProperty("user.language");

        // Tries to set language to system language. If language not found in ressources it will be set to en.json
        try {
            setLanguage(language);
        } catch (Exception e) {
            try {
                setLanguage("en");
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Language getInstance() {
        return instance;
    }

    public static String getText(String str) {
        String returnStr = "";

        // Try to find string in the JSON object. If not found default to english and try again.
        try {
            returnStr = list.get(str).toString();

        } catch (Exception e) {
            if(!Language.language.equals("en")) {
                String langInUse = Language.language;

                try {
                    setLanguage("en");

                    returnStr = getText(str);

                    setLanguage(langInUse);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            else
                System.out.println("String " + str + " is missing.");
        }

        return returnStr;
    }

    // Sets the language by reading json files from ressources
    private static void setLanguage(String language) throws IOException, ParseException {
        Language.language = language;

        // Creates new input stream with the data from the language file
        InputStreamReader streamReader = new InputStreamReader(Objects.requireNonNull(Language.class.getResourceAsStream(language + ".json")), StandardCharsets.UTF_8);

        // Parses the data from the inputstream to a json object
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(streamReader);

        // Saves the data as a "hashMap" for later use
        list = (JSONObject) jsonObject.get(language);
    }

}
