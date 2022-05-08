package com.alejandro.apiblog.web.error;

import com.alejandro.apiblog.app.exception.InvalidArgumentException;
import com.alejandro.apiblog.app.exception.ResourceNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BlogApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleBlogApiInvalidArgumentException(InvalidArgumentException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        BlogApiError blogApiError = BlogApiError.create(
                status.value(),
                ex.getMessage(),
                status.getReasonPhrase()
        );
        return super.handleExceptionInternal(ex, blogApiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBlogApiResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        BlogApiError blogApiError = generateBlogApiError(
                status.value(),
                ex.getMessage(),
                status.getReasonPhrase(),
                null);
        return super.handleExceptionInternal(ex, blogApiError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "Some parameters have incorrect format";
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        BlogApiError blogApiError = generateBlogApiError(
                status.value(),
                message,
                status.getReasonPhrase(),
                errors
        );
        return super.handleExceptionInternal(ex, blogApiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BlogApiError blogApiError = generateBlogApiError(
                status.value(),
                ex.getMessage(),
                status.getReasonPhrase(),
                null
        );
        return super.handleExceptionInternal(ex, blogApiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "The provided parameter or the collection element is of the wrong type";
        BlogApiError blogApiError = generateBlogApiError(
                status.value(),
                message,
                status.getReasonPhrase(),
                null
        );
        return super.handleExceptionInternal(ex, blogApiError, headers, status, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleBlogApiException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Unexpected exception";
        BlogApiError blogApiError = generateBlogApiError(
                status.value(),
                message,
                status.getReasonPhrase(),
                null);
        return super.handleExceptionInternal(ex, blogApiError, new HttpHeaders(), status, request);
    }

    private BlogApiError generateBlogApiError(Integer status, String message, String phrase, List<String> errors) {
        return BlogApiError.create(status, message, phrase, errors);
    }
}
