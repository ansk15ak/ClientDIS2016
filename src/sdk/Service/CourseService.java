package sdk.Service;

import Security.Digester;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.Connection.Connection;
import sdk.Connection.ResponseCallback;
import sdk.Connection.ResponseParser;
import sdk.Models.Course;

import java.util.ArrayList;

/**
 * Denne klasse bygger kaldet til serveren op og sender kaldet videre til connection.
 * Inspireret af kurset Java
 * @see <a href="https://github.com/Distribuerede-Systemer-2016/java-client/tree/master/src/sdk/services" />
 */
public class CourseService {

    private Connection connection;
    private Gson gson;

    public CourseService() {
        this.connection = new Connection();
        this.gson = new Gson();
    }

    /**
     * Hent alle kurser
     * @param currentUser den aktuelle bruger
     * @param responseCallback svar håndteret fra server
     */
    public void getAll(int currentUser, final ResponseCallback<ArrayList<Course>> responseCallback){

        // Den aktuelle bruger krypteres
        String currentuserEncrypt = Digester.encrypt(String.valueOf(currentUser));

        // Eksekver kald til connection - kurser retur ved success, status retur ved error
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/course/" + currentuserEncrypt);
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                // Tager JSON og laver det om til en arraylist
                ArrayList<Course> courses = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Course>>(){}.getType());
                responseCallback.succes(courses);
            }
            public void error(int status) {
                responseCallback.error(status);
            }
        });
    }

    /**
     * Hent alle kursusdeltagere
     * @param courseId Identifier på kurset
     * @param responseCallback svar håndteret fra server
     */
    public void getAllCourseParticipant(int courseId, final ResponseCallback<String> responseCallback){

        // Det ønskede kursus krypteres
        String courseIdEncrypt = Digester.encrypt(String.valueOf(courseId));

        // Eksekver kald til connection - deltagere retur ved success, status retur ved error
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/teacher/course/participant/" + courseIdEncrypt);
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                // JSON dekrypteres
                String courseparticipants = Digester.decrypt(json);
                responseCallback.succes(courseparticipants);
            }
            public void error(int status) {
                responseCallback.error(status);
            }
        });
    }

    /**
     * Hent kursets gennemsnits rating
     * @param name kursets kode
     * @param responseCallback svar håndteret fra server
     */
    public void getCourseAverage(String name, final ResponseCallback<String> responseCallback){

        // Kursets kode krypteres
        String nameEncrypt = Digester.encrypt(name);

        // Eksekver kald til connection - gennemsnits rating retur ved success, status retur ved error
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/teacher/course/average/" + nameEncrypt);
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                // JSON dekrypteres
                String courseaverage = Digester.decrypt(json);
                responseCallback.succes(courseaverage);

            }
            public void error(int status) {
                responseCallback.error(status);
            }
        });
    }
}
