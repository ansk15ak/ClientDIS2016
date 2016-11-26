package View;

/**
 * Created by Junineskov on 16/11/2016.
 */
import Logic.Controller;
import sdk.Connection.ResponseCallback;
import sdk.Models.Review;
import sdk.Service.ReviewService;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentView {



    public void studentMenu(int currentuser, int lectureinput) {

        System.out.println("Velkommen til undervisningsevaluering!" + "\n");
        System.out.println("Tast (1) - Opret en kommentar");
        System.out.println("Tast (2) - Slet en kommentar");
        System.out.println("Tast (3) - Se egne kommentarer");
        System.out.println("Tast (4) - Se alle kommentarer");
        System.out.println("Tast (5) - Slut program");

        Scanner inputReader = new Scanner(System.in);
        int valg = inputReader.nextInt();

        switch (valg) {

            case 1:
                createReview(currentuser, lectureinput);
                studentMenu(currentuser,lectureinput);
                break;

            case 2:
                deleteReview(currentuser, lectureinput);
                studentMenu(currentuser,lectureinput);
                break;

            case 3:
                showOwnReview(currentuser, lectureinput);
                studentMenu(currentuser, lectureinput);
                break;

            case 4:
                showAllReview(currentuser, lectureinput);
                studentMenu(currentuser, lectureinput);
                break;

            case 5:
                System.exit(20);
                break;

            default:
                System.out.println("Forkert indtastet værdi!");
                studentMenu(currentuser, lectureinput);
                break;
        }
    }
    public void createReview(final int currentuser, final int lectureinput) {

        System.out.println("Skriv en kommentar: ");
        Scanner input1 = new Scanner(System.in);
        String komentar = input1.nextLine();

        System.out.println("Giv en votering mellem 1-5, hvor 5 er højest: ");
        Scanner input2 = new Scanner(System.in);
        int rating = input2.nextInt();

        Review review = new Review();

        review.setUserId(currentuser);
        review.setLectureId(lectureinput);
        review.setComment(komentar);
        review.setRating(rating);

        ReviewService reviewService = new ReviewService();
        reviewService.create(review, new ResponseCallback<Boolean>() {
            public void succes(Boolean review) {

                System.out.println("Dit review er nu oprette!");
            }

            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });
    }

    public void deleteReview( int currentuser, final int lectureinput) {

        showOwnReview(currentuser,lectureinput);

        System.out.println("Vælg det review du ønsker at slette: ");
        Scanner input1 = new Scanner(System.in);
        int reviewDelete = input1.nextInt();

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


    public void showOwnReview(final int currentuser, final int lectureinput) {

        System.out.println("Alle dine kommentarer for " + lectureinput +  " oprettet af " + currentuser);

        ReviewService reviewService = new ReviewService();

        reviewService.getAllFromUsers(currentuser, new ResponseCallback<ArrayList<Review>>() {
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
}