package se.magnus.util.http;

import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;

public record HttpErrorInfo(ZonedDateTime timeZone,  HttpStatus httpStatus, String path, String message)  {}
