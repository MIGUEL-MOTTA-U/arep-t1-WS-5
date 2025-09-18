package eci.edu.co.jpa_ws_arep.config;

import eci.edu.co.jpa_ws_arep.errors.ErrorCustomController;
import eci.edu.co.jpa_ws_arep.errors.ErrorCustomRepository;
import eci.edu.co.jpa_ws_arep.models.types.ErrorDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(ErrorCustomRepository.class)
    public ResponseEntity<ErrorDetail> handleException(ErrorCustomRepository e) {
        logger.error(e.getMessage());
        return ResponseEntity.status(e.getServerCustomResponse().getCode()).body(
                new ErrorDetail(
                        e.getServerCustomResponse().getResponseMessage(),
                        e.getServerCustomResponse().getCode(),
                        "There has been an error with the data source... Contact the administrator if the problem persists."
                )
        );
    }

    @ExceptionHandler(ErrorCustomController.class)
    public ResponseEntity<ErrorDetail> handleException(ErrorCustomController e) {
        return ResponseEntity.status(e.getServerCustomResponse().getCode()).body(
                new ErrorDetail(
                        e.getServerCustomResponse().getResponseMessage(),
                        e.getServerCustomResponse().getCode(),
                        "There has been an error with the request: " + e.getMessage()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleException(Exception e) {
        logger.warn("There has been an unexpected exception... ");
        logger.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorDetail(
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "There has been an unexpected error... Contact the administrator if the problem persists."
                )
        );
    }
}
