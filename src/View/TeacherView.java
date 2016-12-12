package View;

import Logic.Controller;
import sdk.Connection.ResponseCallback;
import sdk.Models.Review;
import sdk.Service.CourseService;
import sdk.Service.LectureService;
import sdk.Service.ReviewService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Denne klasse er lærerens indgang til klienten.
 * Inspireret af kurset Java
 * @see <a href="https://github.com/Distribuerede-Systemer-2016/java-client/tree/master/src/view" />
 */
public class TeacherView extends UserView{

    public void teacherMenu(int currentuser, int lectureinput) {

        System.out.println("Velkommen til undervisningsevaluering!" + "\n");
        System.out.println("Tast (1) - Se statistik for deltagelse");
        System.out.println("Tast (2) - Se statistik for gennemsnit af kursus");
        System.out.println("Tast (3) - Slet en kommentar");
        System.out.println("Tast (4) - Slut program");

        // Vælg den ønskede handling
        Scanner inputReader = new Scanner(System.in);
        int valg = inputReader.nextInt();

        // Udfør den ønskede handling
        switch (valg) {
            case 1:
                showCourseParticipant();
                break;
            case 2:
                showCourseAverage();
                break;
            case 3:
                deleteReviewTeacher(currentuser, lectureinput);
                break;
            case 4:
                System.exit(20);
                break;
            default:
                System.out.println("Forkert indtastet værdi!");
                break;
        }

        // Vis lærer view
        Controller controller = new Controller();
        controller.showTeacherView(currentuser, lectureinput);
    }

    /**
     * Slet et review (soft delete)
     * @param currentuser den aktuelle bruger
     * @param lectureinput den ønskede lektion
     */
    public void deleteReviewTeacher(int currentuser, int lectureinput) {

        // vis alle reviews
        showAllReview(currentuser,lectureinput);

        // Vælg review der ønskes slettet
        System.out.println("Vælg det review du ønsker at slette: ");
        Scanner input1 = new Scanner(System.in);
        int reviewDeleteTeacher = input1.nextInt();

        // Slet review via review servicen
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

    /**
     * Vis alle review for en given lektion
     * @param currentuser den aktuelle bruger
     * @param lectureinput den ønskede lektion
     */
    public void showAllReview(final int currentuser, final int lectureinput) {

        System.out.println("Alle kommentarer for " + lectureinput);

        // Hent alle reviews for en given lektion via review servicen.
        ReviewService reviewService = new ReviewService();
        reviewService.getAll(lectureinput, new ResponseCallback<ArrayList<Review>>() {
            public void succes(ArrayList<Review> reviews) {

                // Print revies for en given lektion
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

    /**
     * Vis kursus deltagere
     */
    public void showCourseParticipant(){

        // Vælg kursus
        System.out.println("Vælg det kursus id du ønsker at se deltagelse for: ");
        Scanner input2 = new Scanner(System.in);
        int courseId = input2.nextInt();

        // Hent kursets deltagere via course servicen
        CourseService courseService = new CourseService();
        courseService.getAllCourseParticipant(courseId, new ResponseCallback<String>() {
            public void succes(String Data) {
                System.out.println("Deltagelse: " + Data);
            }
            public void error(int status) { System.out.println("Error: " + status); }
        });

    }

    /**
     * Vis kursets gennemsnits rating
     */
    public void showCourseAverage(){

        // Vælg kursus
        System.out.println("Vælg det kusus navn du ønsker at se deltagelse for: ");
        Scanner input3 = new Scanner(System.in);
        String name = input3.nextLine();

        // Hent gennemsnits rating for kurset via course servicen.
        CourseService courseService = new CourseService();
        courseService.getCourseAverage(name, new ResponseCallback<String>() {
            public void succes(String Data) {
                System.out.println("Gennemsnittet: " + Data);
            }
            public void error(int status) { System.out.println("Error: " + status); }
        });
    }
}