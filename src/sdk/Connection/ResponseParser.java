package sdk.Connection;

/**
 * Created by Junineskov on 14/11/2016.
 */
public interface ResponseParser {

    void payload(String json);
    void error(int status);

}