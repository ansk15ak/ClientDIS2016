package View;

import Logic.Controller;
import sdk.Connection.ResponseCallback;
import sdk.Models.*;
import sdk.Service.CourseService;
import sdk.Service.LectureService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Denne klasse er brugerens indgang til klienten.
 * Inspireret af kurset Java
 * @see <a href="https://github.com/Distribuerede-Systemer-2016/java-client/tree/master/src/view" />
 */
public class UserView {

    /**
     * Vis alle brugerens kurser.
     * @param currentUser den aktuelle bruger
     * @param currentUserType den aktuelle brugertype
     */
    public void showAllCourses(int currentUser, int currentUserType) {

        CourseService courseService = new CourseService();

        // Hent brugerens kurser via course servicen.
        courseService.getAll(currentUser, new ResponseCallback<ArrayList<Course>>() {
            public void succes(ArrayList<Course> courses) {

                // Print alle brugerens kurser
                for (Course course : courses) {
                    System.out.println("Id:\t " + course.getId());
                    System.out.println("Code: " + course.getCode());
                    System.out.println("Navn: " + course.getDisplaytext());
                    System.out.println("\n");
                }
            }

            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });

        System.out.println("Indtast navn på det kursus, du ønsker at se lektioner for: ");
        Scanner input = new Scanner(System.in);
        String courseinput = input.nextLine();

        // Vis alle lektioner for et given kursus
        showAllLectures(currentUser, courseinput, currentUserType);
    }

    /**
     * Vis alle brugerens lektioner
     * @param currentuser den aktuelle bruger
     * @param courseinput det ønskede kursus
     * @param currentUserType den aktuelle brugertype
     */
    public void showAllLectures(int currentuser, String courseinput, int currentUserType) {

        System.out.println("Du har valgt alle forelæsninger og øvelsesgange for " + courseinput + "\n");

        LectureService lectureService = new LectureService();

        // Hent kursets lektioner via lecture servicen.
        lectureService.getAll(courseinput, new ResponseCallback<ArrayList<Lecture>>() {
            public void succes(ArrayList<Lecture> lectures) {

                // Print alle kursets lektioner
                for (Lecture lecture : lectures) {
                    System.out.print("Id: " + lecture.getId() + " ");
                    System.out.println("Type: " + lecture.getType() + " ");
                    System.out.println("Beskrivelse:  " + lecture.getDescription() + " ");
                    System.out.println("Lokation: " + lecture.getLocation() + " ");
                    System.out.print("\n");
                }
            }

            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });

        System.out.println("Vælg nu den lektion, du ønsker at gøre noget ved: ");
        Scanner input = new Scanner(System.in);

        int lectureinput = input.nextInt();

        // Vis brugerbestemt view
        Controller controller = new Controller();
        if (currentUserType == 1) {
            controller.showStudentView(currentuser, lectureinput);
        }
        if (currentUserType == 2) {
            controller.showTeacherView(currentuser, lectureinput);
        }
    }
}

