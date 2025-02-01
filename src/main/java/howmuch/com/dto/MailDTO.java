package howmuch.com.dto;

import lombok.Data;

@Data
public class MailDTO {
	private String To;
	private String From;
	private String Title;
	private String Message;
}
