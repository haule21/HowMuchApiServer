package howmuch.com.dto;

import java.sql.Date;

import org.springframework.data.repository.NoRepositoryBean;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsersDTO {
	private String UserId;
	private String Password;
	private String Name;
	private String StoreNumber;
	private String ROLE;
	private String SubscriptionId;
	private Date CreateDate;
	private Date UpdateDate;
	private Date DeleteDate;
}
