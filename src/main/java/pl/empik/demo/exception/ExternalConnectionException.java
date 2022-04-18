package pl.empik.demo.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ExternalConnectionException extends RuntimeException {

  public ExternalConnectionException(String message) {
    super(message);
  }
}
