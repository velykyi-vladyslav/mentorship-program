package velykyi.vladyslav.exception.resolver;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import velykyi.vladyslav.exception.ServiceException;

@ControllerAdvice
public class RestResponseStatusExceptionResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<Object> handleServiceException(ServiceException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
