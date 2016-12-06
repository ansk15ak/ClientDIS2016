package Logic;

import View.LoginView;
import View.StudentView;
import View.TeacherView;
import View.UserView;

/**
 * Denne klasse styrer hvilket view der skal vises på klienten.
 */
public class Controller {

    private LoginView loginView;
    private StudentView studentView;
    private TeacherView teacherView;
    private UserView userView;

    /**
     * Views initieres
     */
    public Controller() {
        this.loginView = new LoginView();
        this.studentView = new StudentView();
        this.teacherView = new TeacherView();
        this.userView = new UserView();
    }

    /**
     * Viser Login view
     */
    public void showLoginView() {
        this.loginView.loginMenu();
    }

    /**
     * Viser lærer view
     * @param currentuser den aktuelle lærer
     * @param lectureinput den ønskede lektion
     */
    public void showTeacherView(int currentuser, int lectureinput) {
        this.teacherView.teacherMenu(currentuser, lectureinput);
    }

    /**
     * Viser studerende view
     * @param currentuser den aktuelle studerende
     * @param lectureinput den ønskede lektion
     */
    public void showStudentView(int currentuser, int lectureinput) {
        this.studentView.studentMenu(currentuser, lectureinput);
    }

    /**
     * Viser bruger view
     * @param currentuser den aktuelle bruger
     * @param currentusertype den aktuelle brugertype
     */
    public void showUserView(int currentuser, int currentusertype) {
        this.userView.showAllCourses(currentuser,currentusertype);
    }
}