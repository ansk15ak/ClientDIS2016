package sdk.Service;

import Security.Digester;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.Connection.Connection;
import sdk.Connection.ResponseCallback;
import sdk.Connection.ResponseParser;
import sdk.Models.Lecture;
import sdk.Models.User;

import java.util.ArrayList;

/**
 * Created by Junineskov on 14/11/2016.
 */
public class LectureService {
    private Connection connection;
    private Gson gson;

    public LectureService() {
        this.connection = new Connection();
        this.gson = new Gson();
    }


    public void getAll(String courseinput, final ResponseCallback<ArrayList<Lecture>> responseCallback){

        String courseinputEncrypt = Digester.encrypt(String.valueOf(courseinput));

        HttpGet getRequest = new HttpGet(Connection.serverURL + "/lecture/" + courseinputEncrypt);
        //kald på execute metoden med dens to argumenter
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //Tager Json og laver det om til en arraylist, og dervd gemme den i books.
                ArrayList<Lecture> lectures = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Lecture>>(){}.getType());
                responseCallback.succes(lectures);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }
}
