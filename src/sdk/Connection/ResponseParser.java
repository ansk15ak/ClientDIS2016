package sdk.Connection;

/**
 * Interface ResponseParser som kræver parsing af to typer udfald ved svar til services.
 */
public interface ResponseParser {

    void payload(String json);
    void error(int status);

}