package in.spdev.blogapp.exception;

import in.spdev.blogapp.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException){
        String responseMessage = resourceNotFoundException.getMessage();
        ApiResponse apiResponse = new ApiResponse(responseMessage,false);
        return  new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
