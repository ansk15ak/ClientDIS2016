package View;

import Logic.Controller;
import sdk.Connection.ResponseCallback;
import sdk.Models.Review;
import sdk.Models.Teacher;
import sdk.Service.ReviewService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Junineskov on 16/11/2016.
 */
public class TeacherView extends UserView{

    public void teacherMenu(int currentuser, int lectuinput) {
        System.out.println("Velkommen til undervisningsevaluering!" + "\n");

        //prøvede at extended userview i håb om at de to manglende variabler ville komme med over...
        //showAllCourses(currentuser);
        //showAllLectures(currentuser, courseinput);

        //UserView userView = new UserView();
        //userView.showAllCourses(currentuser);

        showAllReview(currentuser, lectuinput);

        System.out.println("Tast (1) - Se gennemsnit for rating");
        System.out.println("Tast (2) - Slet en kommentar");
        System.out.println("Tast (3) - Slut program");

        Scanner inputReader = new Scanner(System.in);
        int valg = inputReader.nextInt();

        switch (valg) {

            case 1:
                showAverage();
                break;

            case 2:
                deleteComment();
                break;

            case 3:
                System.exit(20);
                break;

            default:
                System.out.println("Forkert indtastet værdi!");
                teacherMenu(currentuser, lectuinput);
                break;
        }
    }

    public void showAllReview(final int currentuser, final int lectureinput) {

        System.out.println("Alle kommentarer for " + lectureinput);

        ReviewService reviewService = new ReviewService();

        reviewService.getAll(lectureinput, new ResponseCallback<ArrayList<Review>>() {
            public void succes(ArrayList<Review> reviews) {
                for (Review review : reviews) {
                    System.out.println("User_Id: " + review.getUserId());
                    System.out.println("Lecture_Id: " + review.getLectureId());
                    System.out.println("Comment: " + review.getComment());
                    System.out.println("rating: " + review.getRating());
                    System.out.println("\n");
                }
            }

            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });

    }
    public void showAverage(){
        //Print ud gennemsnittet af rating
        System.out.println("læg alle rating sammen og divider med antallet" /*getReview.average*/);

    }
    public void deleteComment(){

    }
}