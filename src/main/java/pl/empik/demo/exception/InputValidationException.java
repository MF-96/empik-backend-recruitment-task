package pl.empik.demo.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InputValidationException extends RuntimeException {

  public InputValidationException(String message) {
    super(message);
  }
}
