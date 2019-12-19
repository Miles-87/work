package pl.michonskim.works.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.michonskim.works.exception.CompanyAlreadyExistException;
import pl.michonskim.works.exception.CompanyNotFoundException;
import pl.michonskim.works.exception.ErrorResponse;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CompanyNotFoundException.class})
    public ResponseEntity<ErrorResponse> sendCompanyNotFoundException(CompanyNotFoundException e) {
        String message = MessageFormat.format("Company not found", e.getMessage());
        logger.error(message);
        return new ResponseEntity<>(new ErrorResponse(message, LocalDateTime.now()), HttpStatus.OK);
    }

    @ExceptionHandler({CompanyAlreadyExistException.class})
    public @ResponseBody
    ResponseEntity<ErrorResponse> sendCompanyAlreadyExistException() {
        return new ResponseEntity<>(new ErrorResponse("Already Exist", LocalDateTime.now()),HttpStatus.CONFLICT);
    }


}
