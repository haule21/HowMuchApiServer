package howmuch.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import howmuch.com.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public int DeleteUser(String userId) {
		return accountRepository.delete(userId);
	}
}
