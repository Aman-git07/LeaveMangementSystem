package leaveManagement.backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionException {

    //Validation error handling
    public ResponseEntity<Map<String,String>> handleValidationException (MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = ((FieldError)error).getDefaultMessage();
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Handling general exceptions
    public ResponseEntity<String> handleGlobalExceptions(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
