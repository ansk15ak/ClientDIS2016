package View;

import javax.print.DocFlavor;
import java.util.Scanner;
import Security.Digester;
import sdk.Connection.ResponseCallback;
import sdk.Models.Student;
import sdk.Models.User;
import sdk.Service.UserService;

/**
 * Created by Junineskov on 16/11/2016.
 */
public class LoginView {
    private UserService userService;

    public void loginMenu() {

        System.out.println("Velkommen til undervisningsevaluering!" + "\n");
        Scanner input1 = new Scanner(System.in);
        System.out.println("CBSMail: ");
        String mail = input1.nextLine();
        Scanner input2 = new Scanner(System.in);
        System.out.println("Password: ");
        String password = input2.nextLine();


        //husk hashing og hvad med configloader!
        String securedPW = Digester.hashWithSalt(password);

        UserService userService = new UserService();
        userService.login(mail, securedPW, new ResponseCallback<User>() {
            public void succes(User user) {

                if (user == null) {
                    System.out.println("Forkert login");
                } else {
                    System.out.println("Hej, " + user.getCbsMail() + "!");

                    int currentuser = user.getId();

                    int currentUserType = 0;

                    if (user.getType().contentEquals("student")) {
                        currentUserType = 1;
                        UserView userView = new UserView();
                        userView.showAllCourses(currentuser, currentUserType);
                    }
                    if (user.getType().contentEquals("teacher")) {
                        currentUserType = 2;
                        UserView userView = new UserView();
                        userView.showAllCourses(currentuser, currentUserType);
                    }
                    if (user.getType().contentEquals("admin")) {
                        System.out.println("Du kan ikke benytte klienten som admin!");
                    }
                }

            }

            public void error(int status) {
                System.out.println("Noget gik galt, error: " + status);
            }
        });
    }
}