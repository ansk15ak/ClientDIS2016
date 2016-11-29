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
 * Created by Junineskov on 14/11/2016.
 */

public class UserService {

    private Gson gson;
    private Connection connection;


    public UserService(){

        gson = new Gson();
    }

    public void login(String mail, String password, final ResponseCallback<User> responseCallback){

        HttpPost postRequest = new HttpPost(Connection.serverURL + "/login");

        User login = new User();
        login.setCbsMail(mail);
        login.setPassword(password);

        try {
            StringEntity loginInfo = new StringEntity(Digester.encrypt(gson.toJson(login)));
            postRequest.setEntity(loginInfo);
            postRequest.setHeader("Content-Type", "application/json");

            Connection connection = new Connection();
            connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
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