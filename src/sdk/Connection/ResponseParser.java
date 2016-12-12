package sdk.Connection;

/**
 * Interface ResponseParser som kræver parsing af to typer udfald ved svar til services.
 * Inspireret af kurset Java
 * @see <a href="https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/connection/ResponseParser.java" />
 */
public interface ResponseParser {

    void payload(String json);
    void error(int status);

}