package View;

import Logic.Controller;
import sdk.Connection.ResponseCallback;
import sdk.Models.Review;
import sdk.Service.ReviewService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Denne klasse er den studerendes indgang til klienten.
 * Inspireret af kurset Java
 * @see <a href="https://github.com/Distribuerede-Systemer-2016/java-client/tree/master/src/view" />
 */
public class StudentView {

    public void studentMenu(int currentuser, int lectureinput) {

        System.out.println("Velkommen til undervisningsevaluering!" + "\n");
        System.out.println("Tast (1) - Opret en kommentar");
        System.out.println("Tast (2) - Slet en kommentar");
        System.out.println("Tast (3) - Se egne kommentarer");
        System.out.println("Tast (4) - Se alle kommentarer");
        System.out.println("Tast (5) - Slut program");

        // Vælg den ønskede handling
        Scanner inputReader = new Scanner(System.in);
        int valg = inputReader.nextInt();

        // Udfør den ønskede handling
        switch (valg) {
            case 1:
                createReview(currentuser, lectureinput);
                break;
            case 2:
                deleteReview(currentuser, lectureinput);
                break;
            case 3:
                showOwnReview(currentuser, lectureinput);
                break;
            case 4:
                showAllReview(currentuser, lectureinput);
                break;
            case 5:
                System.exit(20);
                break;
            default:
                System.out.println("Forkert indtastet værdi!");
                break;
        }

        // Vis Studerende view
        Controller controller = new Controller();
        controller.showStudentView(currentuser, lectureinput);
    }

    /**
     * Opret et review.
     * @param currentuser den aktuelle bruger
     * @param lectureinput den ønskede lektion
     */
    public void createReview(final int currentuser, final int lectureinput) {

        // Skriv review kommentar
        System.out.println("Skriv en kommentar: ");
        Scanner input1 = new Scanner(System.in);
        String komentar = input1.nextLine();

        // Skriv review score
        System.out.println("Giv en votering mellem 1-5, hvor 5 er højest: ");
        Scanner input2 = new Scanner(System.in);
        int rating = input2.nextInt();

        Review review = new Review();
        review.setUserId(currentuser);
        review.setLectureId(lectureinput);
        review.setComment(komentar);
        review.setRating(rating);

        // Opret review via review servicen.
        ReviewService reviewService = new ReviewService();
        reviewService.create(review, new ResponseCallback<Boolean>() {
            public void succes(Boolean review) { System.out.println("Dit review er nu oprettet!"); }
            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });
    }

    /**
     * Slet review.
     * @param currentuser den aktuelle bruger
     * @param lectureinput den ønskede lektion
     */
    public void deleteReview( int currentuser, final int lectureinput) {

        // Vis den aktuelle studerendes reviews
        showOwnReview(currentuser,lectureinput);

        // Vælg det review der ønskes slettet
        System.out.println("Vælg det review du ønsker at slette: ");
        Scanner input1 = new Scanner(System.in);
        int reviewDelete = input1.nextInt();

        // Slet review via review servicen.
        ReviewService reviewService = new ReviewService();
        reviewService.delete(reviewDelete, currentuser, new ResponseCallback<Boolean>() {
            public void succes(Boolean Data) {
                System.out.println("Dit review er nu slettet!");
            }
            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });
    }

    /**
     * Vis alle reviews.
     * @param currentuser den aktuelle bruger
     * @param lectureinput den ønskede lektion
     */
    public void showAllReview(final int currentuser, final int lectureinput) {

        System.out.println("Alle kommentarer for " + lectureinput);

        // Hent alle reviews via review servicen.
        ReviewService reviewService = new ReviewService();
        reviewService.getAll(lectureinput, new ResponseCallback<ArrayList<Review>>() {
            public void succes(ArrayList<Review> reviews) {

                // Print alle reviews
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
     * Vis egne reviews.
     * @param currentuser den aktuelle bruger.
     * @param lectureinput den ønskede lektion.
     */
    public void showOwnReview(final int currentuser, final int lectureinput) {

        System.out.println("Alle dine kommentarer for " + lectureinput +  " oprettet af " + currentuser);

        // Hent egne reviews via review servicen.
        ReviewService reviewService = new ReviewService();
        reviewService.getAllByUser(currentuser, new ResponseCallback<ArrayList<Review>>() {
            public void succes(ArrayList<Review> reviews) {

                // Print egne reviews
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
}