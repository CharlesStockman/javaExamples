package se.magnus.util.http;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

public record HttpErrorInfo(ZonedDateTime timestamp, String path, HttpStatus httpStatus, String message)  {}
