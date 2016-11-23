package sdk.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.Connection.Connection;
import sdk.Connection.ResponseCallback;
import sdk.Connection.ResponseParser;
import sdk.Models.Course;
import sdk.Models.Lecture;
import sdk.Models.User;

import java.util.ArrayList;

/**
 * Created by Junineskov on 14/11/2016.
 */
public class CourseService {
    private Connection connection;
    private Gson gson;
    private AccessService accessService;

    public CourseService() {
        this.connection = new Connection();
        this.gson = new Gson();
        this.accessService = new AccessService();
    }


    public void getAll(int currentUser, final ResponseCallback<ArrayList<Course>> responseCallback){

        //den måde man lave get request på

        HttpGet getRequest = new HttpGet(Connection.serverURL + "/course/" + currentUser);
        //kald på execute metoden med dens to argumenter
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //Tager Json og laver det om til en arraylist, og dervd gemme den i books.
                ArrayList<Course> courses = gson.fromJson(json, new TypeToken<ArrayList<Course>>(){}.getType());
                responseCallback.succes(courses);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }
}
