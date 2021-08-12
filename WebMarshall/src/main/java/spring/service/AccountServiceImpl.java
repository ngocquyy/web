package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.AccountDAO;
import spring.entity.Accounts;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDAO accountDAO;

	@Override
	public List<Accounts> findAllAcc(Integer offset, Integer maxResult) {
		// TODO Auto-generated method stub
		return accountDAO.findAllAcc(offset, maxResult);
	}

	@Override
	public Long getTotalAccounts() {
		// TODO Auto-generated method stub
		return accountDAO.getTotalAccounts();
	}

	@Override
	public Accounts findByUserName(String userName) {
		// TODO Auto-generated method stub
		return accountDAO.findByUserName(userName);
	}

	@Override
	public Accounts createAcc(Accounts user) {
		// TODO Auto-generated method stub
		return accountDAO.createAcc(user);
	}

	@Override
	public void update(Accounts user) {
		// TODO Auto-generated method stub
		accountDAO.update(user);
	}

	@Override
	public Accounts delete(String userId) {
		// TODO Auto-generated method stub
		return accountDAO.delete(userId);
	}
	
}
