package sdk.Service;

/**
 * Created by Junineskov on 17/11/2016.
 */
import sdk.Models.User;

public class AccessService {

    private static User user;

    public static void setAccessToken(User token){
        user = token;
    }
    public static User getAccessToken(){
        return user;
    }
    public static void clear(){
        user = null;
    }

}