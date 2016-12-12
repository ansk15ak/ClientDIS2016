package sdk.Connection;

import Logic.ConfigLoader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Denne klasse skaber forbindelse til Server.
 * Inspireret af kurset Java
 * @see <a href="https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/connection/Connection.java" />
 */
public class Connection {

    // Henter Server url fra konfiguration
    public static String serverURL = ConfigLoader.SERVER_URL;

    private CloseableHttpClient httpClient;

    // Opretter en CloseableHttpClient instans med standard konfiguration
    public Connection(){
        this.httpClient = HttpClients.createDefault();
    }

    public void execute(HttpUriRequest uriRequest, final ResponseParser parser){

        // Opretter en standard response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            // Håndterer response fra serveren
            public String handleResponse(final HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    parser.error(status);
                }
                return null;
            }
        };

        try {
            /* Eksekverer HTTP forespørgsel i en standard kontekst og
             behandler svaret ved brug af en response handler. */
            String json = this.httpClient.execute(uriRequest, responseHandler);

            // Parser json svaret til payload
            parser.payload(json);

        } catch (IOException e) {
            System.out.println("Åben serveren... Tak ");
        }
    }
}
