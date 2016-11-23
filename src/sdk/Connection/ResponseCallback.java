package sdk.Connection;

/**
 * Created by Junineskov on 14/11/2016.
 */
//bruger kan bestemme hvad T skal være, når man bruger responsecallback metode

public interface ResponseCallback<T> {

    void succes(T Data);
    void error(int status);

}
