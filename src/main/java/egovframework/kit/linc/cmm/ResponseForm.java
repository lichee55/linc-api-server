package egovframework.kit.linc.cmm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseForm<T> {

	private T data;
	private String message;
	private int status;

}
