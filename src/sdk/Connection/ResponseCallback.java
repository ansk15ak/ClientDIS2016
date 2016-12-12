package sdk.Connection;

/**
 * Interface ResponseCallback som kræver implementering af to typer udfald ved svar til views.
 * Inspireret af kurset Java
 * @see <a href="https://github.com/Distribuerede-Systemer-2016/java-client/blob/master/src/sdk/connection/ResponseCallback.java" />
 */
public interface ResponseCallback<T> {

    void succes(T Data); //bruger kan bestemme hvad T skal være, når man bruger responsecallback metode
    void error(int status);
}
