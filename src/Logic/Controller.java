package Logic;

/**
 * Created by Junineskov on 16/11/2016.
 */

import View.LoginView;
import View.StudentView;
import View.TeacherView;
import View.UserView;

//Skal der være flere controllere? se på anders's controller på et tidspunkt.

public class Controller {

    private LoginView loginView;
    private StudentView studentView;
    private TeacherView teacherView;
    private UserView userView;


    public Controller() {
        this.loginView = new LoginView();
        this.studentView = new StudentView();
        this.teacherView = new TeacherView();
        this.userView = new UserView();
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void showLoginView() {
        this.loginView.loginMenu();
    }

    public StudentView getStudentView() {
        return studentView;
    }

    public void showStudentView(int currentuser, int lectureinput) {
        this.studentView.studentMenu(currentuser,lectureinput);
    }

    public TeacherView getTeacherView() {
        return teacherView;
    }

    public void showTeacherView() {
    }
    public UserView getUserView() {
        return userView;
    }

}