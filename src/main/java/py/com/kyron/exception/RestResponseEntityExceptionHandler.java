package py.com.kyron.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SpringBootDemoException.class)
	public ResponseEntity<ErrorMessage> handleSpringBootDemoException(SpringBootDemoException springBootDemoException, WebRequest request){
		
		ErrorMessage message = new ErrorMessage(springBootDemoException.getHttpStatus(), springBootDemoException.getMessage());
		return ResponseEntity.status(message.getStatus())
                .body(message);
	}
}
