package sdk.Connection;

/**
 * Interface ResponseCallback som kræver implementering af to typer udfald ved svar til views.
 */
public interface ResponseCallback<T> {

    void succes(T Data); //bruger kan bestemme hvad T skal være, når man bruger responsecallback metode
    void error(int status);
}
