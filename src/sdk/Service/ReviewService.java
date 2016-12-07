package sdk.Service;

import Security.Digester;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import sdk.Connection.Connection;
import sdk.Connection.ResponseCallback;
import sdk.Connection.ResponseParser;
import sdk.Models.Review;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Denne klasse bygger kaldet til serveren op og sender kaldet videre til connection.
 */
public class ReviewService {

    private Connection connection;
    private Gson gson;

    public ReviewService() {
        this.connection = new Connection();
        this.gson = new Gson();
    }

    /**
     * Hent alle reviews på en lektion
     * @param lectureinput den aktuelle lektion
     * @param responseCallback svar håndteret fra server
     */
    public void getAll(int lectureinput, final ResponseCallback<ArrayList<Review>> responseCallback){

        // Den aktuelle lektion krypteres
        String lectureinputEncrypt = Digester.encrypt(String.valueOf(lectureinput));

        // Eksekver kald til connection - reviews retur ved success, status retur ved error
        //den måde man lave get request på
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/review/" + lectureinputEncrypt);
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                // Tager JSON og laver det om til en arraylist
                ArrayList<Review> reviews = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.succes(reviews);
            }
            public void error(int status) { responseCallback.error(status); }
        });
    }

    /**
     * Henter alle reviews oprettet af brugeren
     * @param currentUser den aktulle bruger
     * @param responseCallback svar håndteret fra server
     */
    public void getAllByUser(int currentUser, final ResponseCallback<ArrayList<Review>> responseCallback){

        // Den aktuelle bruger krypteres
        String currentUserEncrypt = Digester.encrypt(String.valueOf(currentUser));

        // Eksekver kald til connection - reviews fra brugeren retur ved success, status retur ved error
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/reviews/" + currentUserEncrypt);
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                // Tager JSON og laver det om til en arraylist
                ArrayList<Review> reviews = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.succes(reviews);
            }
            public void error(int status) { responseCallback.error(status); }
        });
    }

    /**
     * Slet et review oprette af den studerende
     * @param reviewDelete det aktuelle review
     * @param currentuser den aktuelle bruger
     * @param responseCallback svar håndteret fra server
     */
    public void delete(int reviewDelete, int currentuser,  final ResponseCallback<Boolean> responseCallback){

        // Det aktuelle review krypteres
        String integer = String.valueOf(reviewDelete);
        String reviewDeleteEncrypt = Digester.encrypt(integer);

        // Den aktuelle bruger krypteres
        String integer1 = String.valueOf(currentuser);
        String currentuserEncrypt = Digester.encrypt(integer1);

        // Eksekver kald til connection - en meddelse retur ved success, status retur ved error
        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/student/review/" + reviewDeleteEncrypt +"/" + currentuserEncrypt);
        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {

                // Spørg
                responseCallback.succes(true);
            }
            public void error(int status) { responseCallback.error(status); }
        });
    }

    /**
     * Slet et review oprette af de studerende
     * @param reviewDeleteTeacher det aktuelle review
     * @param responseCallback svar håndteret fra server
     */
    public void deleteTeacher(int reviewDeleteTeacher, final ResponseCallback<Boolean> responseCallback) {

        // Det aktuelle review krypteres
        String integer = String.valueOf(reviewDeleteTeacher);
        String reviewDeleteTeacherEncrypt = Digester.encrypt(integer);

        // Eksekver kald til connection - en meddelse retur ved success, status retur ved error
        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/teacher/review/" + reviewDeleteTeacherEncrypt);
        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {

                // Spørg
                responseCallback.succes(true);
            }
            public void error(int status) { responseCallback.error(status); }
        });
    }

    /**
     * Opret et review
     * @param review det aktuelle review
     * @param responseCallback svar håndteret fra server
     */
    public void create(Review review, final ResponseCallback<Boolean> responseCallback){

        try {
            // Eksekver kald til connection - en meddelse retur ved success, status retur ved error
            HttpPost postRequest = new HttpPost(Connection.serverURL + "/student/review/");
            postRequest.addHeader("Content-Type","application/json");
            StringEntity jsonReview = new StringEntity(Digester.encrypt(gson.toJson(review)));
            postRequest.setEntity(jsonReview);
            connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {

                    // Spørg
                    responseCallback.succes(true);
                }
                public void error(int status) { responseCallback.error(status); }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
