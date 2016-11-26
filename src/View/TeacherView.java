package View;

import Logic.Controller;
import sdk.Connection.ResponseCallback;
import sdk.Models.Course;
import sdk.Models.Review;
import sdk.Models.Teacher;
import sdk.Service.CourseService;
import sdk.Service.ReviewService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Junineskov on 16/11/2016.
 */
public class TeacherView extends UserView{

    public void teacherMenu(int currentuser, int lectuinput) {
        System.out.println("Velkommen til undervisningsevaluering!" + "\n");

        System.out.println("Tast (1) - Se statistik for deltagelse");
        System.out.println("Tast (2) - Slet en kommentar");
        System.out.println("Tast (3) - Slut program");

        Scanner inputReader = new Scanner(System.in);
        int valg = inputReader.nextInt();

        switch (valg) {

            case 1:
                showCourseParticipant();
                /// /showStatistics(currentuser,lectuinput);
                teacherMenu(currentuser, lectuinput);
                break;

            case 2:
                deleteReviewTeacher(currentuser, lectuinput);
                teacherMenu(currentuser, lectuinput);
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

    //Denne delete funktion vil på den ene eller den anden måde altid mangle noget for at opfyde kravspek. Denne sletter det hele, så rating heller ikke kommer med i for eksempel gennemsnittet.
    public void deleteReviewTeacher(int currentuser, int lectureinput) {

        showAllReview(currentuser,lectureinput);

        System.out.println("Vælg det review du ønsker at slette: ");
        Scanner input1 = new Scanner(System.in);
        int reviewDeleteTeacher = input1.nextInt();

        ReviewService reviewService = new ReviewService();
        reviewService.deleteTeacher(reviewDeleteTeacher, new ResponseCallback<Boolean>() {
            public void succes(Boolean Data) {
                System.out.println("Det review er nu slettet!");
            }

            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });
    }

    public void showAllReview(final int currentuser, final int lectureinput) {

        System.out.println("Alle kommentarer for " + lectureinput);

        ReviewService reviewService = new ReviewService();

        reviewService.getAll(lectureinput, new ResponseCallback<ArrayList<Review>>() {
            public void succes(ArrayList<Review> reviews) {
                for (Review review : reviews) {
                    System.out.println("ReviewId: " + review.getId());
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
    public void showStatistics(int currentuser, int lectureinput){

        System.out.println("Deltagende for kurset: " + "" + "studerende");
        System.out.println("Gennemsnittet af deltagende for kurset: ");
        System.out.println("Deltagende for det modul: " + "" +  "studerende");
        System.out.println("Gennemsnittet af deltagende for modul: ");


    }

    public void showCourseParticipant() {

        CourseService courseService = new CourseService();


        int courseId = 1;
        courseService.getAllCourseParticipant(courseId, new ResponseCallback<ArrayList<Course>>() {
            public void succes(ArrayList<Course> courses) {
                for (Course course : courses){
                    courses.size();
                }


            }

            public void error(int status) {
                System.out.println("Error: " + status);

            }
        });
    }
}