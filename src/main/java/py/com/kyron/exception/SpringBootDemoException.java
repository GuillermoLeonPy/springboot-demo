package py.com.kyron.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class SpringBootDemoException extends Exception {

	private static final long serialVersionUID = 5500938390318815188L;
	private HttpStatus httpStatus;
	
	
	public SpringBootDemoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public SpringBootDemoException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
		// TODO Auto-generated constructor stub
	}	

}
