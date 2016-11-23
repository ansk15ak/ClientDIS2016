package View;

import sdk.Connection.ResponseCallback;
import sdk.Models.Course;
import sdk.Models.Lecture;
import sdk.Models.Review;
import sdk.Service.CourseService;
import sdk.Service.LectureService;
import sdk.Service.ReviewService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Junineskov on 16/11/2016.
 */
public class UserView {

    // denne metode skal ikke bruges da brugeren er logget ind og herved har nogle kurser tilknyttet sig
    // Så det er det kun hvis teacher skal kunne have flere kurser.
    /*public void showAllStudies() {
        System.out.println("Indtast det id for ");

        CourseService courseService = new CourseService();

        courseService.getAll(new ResponseCallback<ArrayList<Course>>() {
            public void succes(ArrayList<Course> courses) {
                for (Course course : courses) {
                    System.out.println("Id: " + course.getId());
                    System.out.println("Code: " + course.getCode());
                    System.out.println("Study_Id: " + course.getStudy_id());
                    System.out.println("Name: " + course.getName());

                    //Ved ikke hvad denne gør, men undersøg det
                        /*    System.out.println("Authors:");

                    for (Author author : book.getAuthors()) {
                        System.out.println("\tName: " + author.getFirstName() + " " + author.getLastName());
                    }*/
            /*    }
            }
            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });
    }*/


    public void showAllCourses(int currentUser) {

        System.out.println("Her er alle dine kurser for " /*+det indtastede study*/);
        CourseService courseService = new CourseService();

        courseService.getAll(currentUser, new ResponseCallback<ArrayList<Course>>() {
            public void succes(ArrayList<Course> courses) {
                    for (Course course : courses) {
                        System.out.println("Id:\t " + course.getId());
                        System.out.println("Code: " + course.getCode());
                        System.out.println("Navn: " + course.getDisplaytext());
                        System.out.println("\n");

                        //Ved ikke hvad denne gør, men undersøg det.
                        /*    System.out.println("Authors:");

                    for (Author author : book.getAuthors()) {
                        System.out.println("\tName: " + author.getFirstName() + " " + author.getLastName());
                    }*
     /*Jeg mangler input til at kunne fange dette specifikke kursus, og herved have dem koblet op på deres lecture.*/
                    }
                }

            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });
        System.out.println("Indtast det navn på det kursus, de ønsker at se moduler for: ");
        Scanner input = new Scanner(System.in);
        String courseinput = input.nextLine();
        showAllLectures(currentUser, courseinput);
    }
    public void showAllLectures(int currentuser, String courseinput) {
        System.out.println("Du har valgt alle forelæsning og øvelsesgang for " + courseinput + "\n");

        LectureService lectureService = new LectureService();

        lectureService.getAll(courseinput, new ResponseCallback<ArrayList<Lecture>>() {
            public void succes(ArrayList<Lecture> lectures) {
                for (Lecture lecture : lectures) {
                    System.out.println("Id:\t\t\t\t\t " + lecture.getId());
                    System.out.println("Type:\t\t\t\t " + lecture.getType());
                    System.out.println("Beskrivelse:  " + lecture.getDescription());
                    System.out.println("Lokation:\t\t " + lecture.getLocation());
                    System.out.println("CourseId:\t\t " + lecture.getCourseId());
                    System.out.println("\n");
                }
            }

            public void error(int status) {
                System.out.println("Error: " + status);
            }
        });
        System.out.println("Vælg nu der modul, de ønsker at gøre noget ved: ");
        Scanner input = new Scanner(System.in);
        int lectureinput = input.nextInt();


        //Overføre ikke currentuser og lectureinput til hverken teacher eller student

        StudentView visMenu = new StudentView();
        visMenu.studentMenu(currentuser, lectureinput);
    }

}