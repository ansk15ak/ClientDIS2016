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
 * Created by Junineskov on 14/11/2016.
 */
public class ReviewService {
    private Connection connection;
    private Gson gson;

    public ReviewService() {
        this.connection = new Connection();
        this.gson = new Gson();
    }


    public void getAll(int lectureinput, final ResponseCallback<ArrayList<Review>> responseCallback){

        String lectureinputEncrypt = Digester.encrypt(String.valueOf(lectureinput));

        //den måde man lave get request på
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/review/" + lectureinputEncrypt);
        //kald på execute metoden med dens to argumenter
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //Tager Json og laver det om til en arraylist, og dervd gemme den i books.
                ArrayList<Review> reviews = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.succes(reviews);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }

    public void getAllByUser(int currentUser, final ResponseCallback<ArrayList<Review>> responseCallback){

        String currentUserEncrypt = Digester.encrypt(String.valueOf(currentUser));

        //den måde man lave get request på
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/reviews/" + currentUserEncrypt);
        //kald på execute metoden med dens to argumenter
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //Tager Json og laver det om til en arraylist, og dervd gemme den i books.
                ArrayList<Review> reviews = gson.fromJson(Digester.decrypt(json), new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.succes(reviews);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }

    public void delete(int reviewDelete, int currentuser,  final ResponseCallback<Boolean> responseCallback){

        String integer = String.valueOf(reviewDelete);
        String reviewDeleteEncrypt = Digester.encrypt(integer);

        String integer1 = String.valueOf(currentuser);
        String currentuserEncrypt = Digester.encrypt(integer1);

        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/student/review/" + reviewDeleteEncrypt +"/" + currentuserEncrypt);
        deleteRequest.addHeader("Content-Type","application/json");


        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {
                responseCallback.succes(true);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }
    public void deleteTeacher(int reviewDeleteTeacher, final ResponseCallback<Boolean> responseCallback) {

        String integer = String.valueOf(reviewDeleteTeacher);
        String reviewDeleteTeacherEncrypt = Digester.encrypt(integer);

        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/teacher/review/" + reviewDeleteTeacherEncrypt);
        deleteRequest.addHeader("Content-Type", "application/json");

        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {
                responseCallback.succes(true);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });
    }

    //der skal nok være nogle andre variabel navne i både slet og opret review.
    public void create(Review review, final ResponseCallback<Boolean> responseCallback){

        try {
            HttpPost postRequest = new HttpPost(Connection.serverURL + "/student/review/");
            postRequest.addHeader("Content-Type","application/json");

            StringEntity jsonReview = new StringEntity(Digester.encrypt(gson.toJson(review)));
            postRequest.setEntity(jsonReview);

            connection.execute(postRequest, new ResponseParser() {
                public void payload(String json) {
                    //Review newReview = gson.fromJson(json, Review.class);
                    responseCallback.succes(true);

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
