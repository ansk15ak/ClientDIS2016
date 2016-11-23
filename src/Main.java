import Logic.ConfigLoader;
import Logic.Controller;
import View.LoginView;
import View.StudentView;
import View.TeacherView;
import View.UserView;
import sdk.Connection.ResponseCallback;
import sdk.Models.*;
import sdk.Service.*;

import java.util.ArrayList;

/**
 * Created by Junineskov on 14/11/2016.
 */
public class Main {

    public static void main(String[] args) {

        ConfigLoader.parseConfig();
        Controller controller = new Controller();
        controller.showLoginView();


        /*UserView userView = new UserView();
        userView.showAllLectures();*/

        /*UserView userView = new UserView();
        userView.showAllLectures();*/

        //De tre nedenstående metoder kalder bare på viewet så der bliver printete menu og select ud for de forskellige.
        /*StudentView studentView = new StudentView();
        studentView.studentMenu();*/

        /*TeacherView teacherView = new TeacherView();
        teacherView.teacherSelect();*/

        /*//POST METODE
        ReviewService bookService = new ReviewService<>();
        Review book = new Review();
        book.setId(1);
        book.setUser_Id(2);
        book.setLecture_Id(199);
        book.setRating("3 Stjerner");
        book.setComment("Kommentar");

        bookService.create(book, new ResponseCallback<Review>() {
            public void succes(Review Data) {

            }

            public void error(int status) {

            }
        });*/
        /*//DELETE METODE for review
        ReviewService reviewService = new ReviewService();
        reviewService.delete("5829a8bb3f5250a6bd2d25d2", new ResponseCallback<Integer>() {
            public void succes(Integer count) {

            }

            public void error(int status) {

            }
        });*/

       /* //GET METODE for study
        StudyService studyService = new StudyService();
        studyService.getAll(new ResponseCallback<ArrayList<Study>>() {

            public void succes(ArrayList<Study> studies) {

                for (Study study : studies) {
                    System.out.println("UserId:\t\t\t" + study.getId());
                    System.out.println("LectureId: " + study.getName());
                    System.out.println("Rating: " + study.getShortname());
                    System.out.println("\n");
                    int i = 1;

                }
            }

            public void error(int status) {

            }

        });*/
        //GET METODE for review
        /*ReviewService reviewService = new ReviewService();
        reviewService.getAll(new ResponseCallback<ArrayList<Review>>() {

            public void succes(ArrayList<Review> reviews) {

                for (Review review : reviews) {
                    System.out.println("UserId:\t\t\t" + review.getUser_Id());
                    System.out.println("LectureId: " + review.getLecture_Id());
                    System.out.println("Rating: " + review.getRating());
                    System.out.println("Comment:\t\t" + review.getComment());
                    System.out.println("Is_Deleted:\t\t" + review.getIs_Deleted());
                    System.out.println("\n");
                    int i = 1;

                }

            }

            public void error(int status) {

            }

        });*/
        /*
        //GET METODE for course
        CourseService courseService = new CourseService();
        courseService.getAll(new ResponseCallback<ArrayList<Course>>() {

            public void succes(ArrayList<Course> courses) {

                for (Course course : courses) {
                    System.out.println("Id:\t\t\t" + course.getId());
                    System.out.println("Code: " + course.getCode());
                    System.out.println("study_Id: " + course.getStudy_id());
                    System.out.println("Name:\t\t" + course.getName());
                    System.out.println("\n");
                    int i = 1;

                }

            }

            public void error(int status) {
                int i = 1;
            }

            ;
        });
        */
        //GET METODE for lectures
       /* LectureService lectureService = new LectureService();
        lectureService.getAll(new ResponseCallback<ArrayList<Lecture>>() {

            public void succes(ArrayList<Lecture> lectures) {

                for (Lecture lecture : lectures) {
                    System.out.println("Id:\t\t\t" + lecture.getId());
                    System.out.println("Type: " + lecture.getType());
                    System.out.println("Beskrivelse: " + lecture.getDescription());
                    System.out.println("Location:\t\t" + lecture.getLocation());
                    System.out.println("Course_Id:\t\t" + lecture.getCourse_Id());
                    System.out.println("\n");
                    int i = 1;

                }

            }

            public void error(int status) {

            }

        });*/
    }
}