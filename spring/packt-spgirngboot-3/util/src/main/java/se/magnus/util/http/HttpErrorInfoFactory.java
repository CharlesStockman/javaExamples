package se.magnus.util.http;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class HttpErrorInfoFactory {

    /**
     * Creates an instance of the HttpErrorInfo
     * @param httpStatus                The Status Code returned from the response
     * @param path                      The context path
     * @param message                   Description of the error
     * @return                          A fully populated HttpErrorInfo
     */
    public static HttpErrorInfo createHttpErrorInfoFactory(HttpStatus httpStatus, String path, String message) {
        return new HttpErrorInfo(ZonedDateTime.now(), httpStatus, path, message);
    }
}
