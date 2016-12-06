package Logic;

/**
 * Denne klasse er hentet direkte fra Serveren
 */

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.util.Map;
import java.util.Set;

/**
 *  ConfigLoader anvendes til at hente den unikke konfiguration til klienten.
 */
public class ConfigLoader {

    // Static variable indlæses alle som String, fordi de hentes fra config.Json.
    public static String HASH_SALT;
    public static String ENCRYPT_KEY;
    public static String DEBUG;
    public static String ENCRYPTION;
    public static String SERVER_URL;

    /* Der oprettes en static final kombineret med SINGLETON(design mønster), som har til formål,
    at kontrollere intialiseringen af klassen for derved at sørge for at objektet ikke kan instantieres mere end en gang */
    private static final ConfigLoader SINGLETON = new ConfigLoader();
    public ConfigLoader getInstance() {
        return SINGLETON;
    }

    // Når metoden ConfigLoader køres, startes parseConfig()
    private ConfigLoader() {
        parseConfig();
    }

    // parseConfig retunerer samtlige værdier fra klient konfigurationen.
    public static void parseConfig() {

        JsonParser jparser = new JsonParser();
        JsonReader jsonReader;

        try {
            jsonReader = new JsonReader(new FileReader("config.json")); // Opretter en reader til at læse klient konfigurationen.
            JsonObject jsonObject = jparser.parse(jsonReader).getAsJsonObject(); // Opretter et JSON objekt af klient konfigurationen.
            Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet(); // Returnerer værdierne i JSON objektet

            for (Map.Entry<String, JsonElement> entry : entries) {
                try {
                    // Sætter static værdierne som strings
                    ConfigLoader.class.getDeclaredField(entry.getKey()).set(SINGLETON, entry.getValue().getAsString());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

