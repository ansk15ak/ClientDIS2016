package sdk.Service;

import Security.Digester;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpGet;
import sdk.Connection.Connection;
import sdk.Connection.ResponseCallback;
import sdk.Connection.ResponseParser;
import sdk.Models.Lecture;

import java.util.ArrayList;

/**
 * Denne klasse bygger kaldet til serveren op og sender kaldet videre til connection.
 */
public class LectureService {

    private Connection connection;
    private Gson gson;

    public LectureService() {
        this.connection = new Connection();
        this.gson = new Gson();
    }

    /**
     * Hent alle lektioner på kurset
     * @param courseinput det aktuelle kursus
     * @param responseCallback svar håndteret fra server
     */
    public void getAll(String courseinput, final ResponseCallback<ArrayList<Lecture>> responseCallback) {

        // Det aktuelle kursus krypteres
        String courseinputEncrypt = Digester.encrypt(String.valueOf(courseinput));

        // Eksekver kald til connection - lektioner retur ved success, status retur ved error
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/lecture/" + courseinputEncrypt);
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                // Tager JSON og laver det om til en arraylist
                ArrayList<Lecture> lectures = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Lecture>>() {
                }.getType());
                responseCallback.succes(lectures);
            }
            public void error(int status) { responseCallback.error(status); }
        });
    }

    /*public void getAllLectureParticipant(int lectureId, final ResponseCallback<String> responseCallback){

        String lectureIdEncrypt = Digester.encrypt(String.valueOf(lectureId));

        HttpGet getRequest = new HttpGet(Connection.serverURL + "/teacher/lecture/" + lectureIdEncrypt);
        //kald på execute metoden med dens to argumenter
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                String he = Digester.decrypt(json);
                responseCallback.succes(he);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });
    }*/

}
