package View;

import java.util.Scanner;

import Logic.Controller;
import Security.Digester;
import sdk.Connection.ResponseCallback;
import sdk.Models.User;
import sdk.Service.UserService;

/**
 * Denne klasse er login billedet på klienten.
 */
public class LoginView {

    private UserService userService;

    public void loginMenu() {

        // Indtast e-mail adresse og kodeord
        System.out.println("Velkommen til undervisningsevaluering!" + "\n");
        Scanner input1 = new Scanner(System.in);
        System.out.println("CBSMail: ");
        String mail = input1.nextLine();
        Scanner input2 = new Scanner(System.in);
        System.out.println("Password: ");
        String password = input2.nextLine();

        // Hash kodeord
        String securedPW = Digester.hashWithSalt(password);

        // Valider bruger i henhold til ResponseCallback interface
        userService = new UserService();
        userService.login(mail, securedPW, new ResponseCallback<User>() {
            public void succes(User user) {

                if (user == null) {
                    System.out.println("Forkert login");
                } else {
                    System.out.println("Hej, " + user.getCbsMail() + "!");

                    // Hent brugertype
                    int currentuser = user.getId();
                    int currentUserType = 0;

                    // Brugeren er studerende
                    if (user.getType().contentEquals("student")) {
                        currentUserType = 1;
                    }

                    // Brugeren er lærer
                    if (user.getType().contentEquals("teacher")) {
                        currentUserType = 2;
                    }

                    // Brugeren er administrator
                    if (user.getType().contentEquals("admin")) {
                        System.out.println("Du kan ikke benytte klienten som admin!");
                    }

                    // Vis brugerbestemt view
                    Controller controller = new Controller();
                    if (currentUserType == 1 || currentUserType ==2) {
                        controller.showUserView(currentuser, currentUserType);
                    } else
                        controller.showLoginView();
                }
            }

            public void error(int status) {
                System.out.println("Noget gik galt, error: " + status);
            }
        });
    }
}