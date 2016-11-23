package sdk.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.Connection.Connection;
import sdk.Connection.ResponseCallback;
import sdk.Connection.ResponseParser;
import sdk.Models.Lecture;
import sdk.Models.Study;

import java.util.ArrayList;

/**
 * Created by Junineskov on 14/11/2016.
 */
public class StudyService {
    private Connection connection;
    private Gson gson;

    public StudyService() {
        this.connection = new Connection();
        this.gson = new Gson();
    }


    public void getAll(final ResponseCallback<ArrayList<Study>> responseCallback){

        //den måde man lave get request på
        String studyId = "BIVKU";
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/study/" + studyId);
        //kald på execute metoden med dens to argumenter
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //Tager Json og laver det om til en arraylist, og dervd gemme den i books.
                ArrayList<Study> studies = gson.fromJson(json, new TypeToken<ArrayList<Study>>(){}.getType());
                responseCallback.succes(studies);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }

}
