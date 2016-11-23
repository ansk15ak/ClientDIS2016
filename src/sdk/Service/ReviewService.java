package sdk.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import sdk.Connection.Connection;
import sdk.Connection.ResponseCallback;
import sdk.Connection.ResponseParser;
import sdk.Models.Delete;
import sdk.Models.Lecture;
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

        //den måde man lave get request på
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/review/" + lectureinput);
        //kald på execute metoden med dens to argumenter
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //Tager Json og laver det om til en arraylist, og dervd gemme den i books.
                ArrayList<Review> reviews = gson.fromJson(json, new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.succes(reviews);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }

    public void getAllFromUsers(int currentUser, final ResponseCallback<ArrayList<Review>> responseCallback){

        //den måde man lave get request på
        HttpGet getRequest = new HttpGet(Connection.serverURL + "/reviews/" + currentUser);
        //kald på execute metoden med dens to argumenter
        connection.execute(getRequest, new ResponseParser() {
            public void payload(String json) {

                //Tager Json og laver det om til en arraylist, og dervd gemme den i books.
                ArrayList<Review> reviews = gson.fromJson(json, new TypeToken<ArrayList<Review>>(){}.getType());
                responseCallback.succes(reviews);

            }

            public void error(int status) {
                responseCallback.error(status);

            }
        });

    }

    public void delete(String id, final ResponseCallback<Integer> responseCallback){

        HttpDelete deleteRequest = new HttpDelete(Connection.serverURL + "/review/");
        deleteRequest.addHeader("Content-Type","application/json");

        connection.execute(deleteRequest, new ResponseParser() {
            public void payload(String json) {
                Delete delete = gson.fromJson(json, Delete.class);
                responseCallback.succes(delete.getCount());

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

            StringEntity jsonBook = new StringEntity(gson.toJson(review));
            postRequest.setEntity(jsonBook);

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
