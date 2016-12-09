package sdk.Service;

import Security.Digester;
import com.google.gson.Gson;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import sdk.Connection.Connection;
import sdk.Connection.ResponseCallback;
import sdk.Connection.ResponseParser;
import sdk.Models.User;

import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executor;

/**
 * Denne klasse bygger kaldet til serveren op og sender kaldet videre til connection.
 */
public class UserService {

    private Gson gson;
    private Connection connection;

    public UserService(){
        gson = new Gson();
    }

    /**
     * Valider login
     * @param mail den aktuelle mail
     * @param password det aktuelle password
     * @param responseCallback svar håndteret fra server
     */
    public void login(String mail, String password, final ResponseCallback<User> responseCallback){

        HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");

        User login = new User();
        login.setCbsMail(mail);
        login.setPassword(password);

        try {
            // Det aktuelle login krypteres
            StringEntity loginInfo = new StringEntity(Digester.encrypt(gson.toJson(login)));
            postRequest.setEntity(loginInfo);
            postRequest.setHeader("Content-Type", "application/json");

            // Eksekver kald til connection - login retur ved success, status retur ved error
            connection = new Connection();
            connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {

                    // Tager JSON og laver det om til en User
                    User userLogin = gson.fromJson(Digester.decrypt(json),User.class);
                    responseCallback.succes(userLogin);
                }
                public void error(int status) {
                    responseCallback.error(status);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}