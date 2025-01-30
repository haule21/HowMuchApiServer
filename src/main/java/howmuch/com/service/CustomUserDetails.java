package howmuch.com.service;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import howmuch.com.dto.UsersDTO;

public class CustomUserDetails implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String UserId;
	private String Password;
	private String Name;
	private String StoreNumber;
	private String ROLE;
	private String SubscriptionId;
	private Date CreateDate;
	private Date UpdateDate;
	private Date DeleteDate;

    public CustomUserDetails(UsersDTO user) {
        this.UserId = user.getUserId();
        this.Password = user.getPassword();
        this.Name = user.getName();
        this.StoreNumber = user.getStoreNumber();
        this.ROLE = user.getROLE();
        this.SubscriptionId = user.getSubscriptionId();
        this.CreateDate = user.getCreateDate();
        this.UpdateDate = user.getUpdateDate();
        this.DeleteDate = user.getDeleteDate();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(ROLE));
    }

    @Override
    public String getPassword() {
        return this.Password;
    }

    @Override
    public String getUsername() {
        return this.UserId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isAccountDeleted(); // 예를 들어 계정이 삭제되지 않았다면
    }

    private boolean isAccountDeleted() {
        // isDeleted가 true일 경우 계정이 비활성화된 것으로 처리
        return false;
    }
}