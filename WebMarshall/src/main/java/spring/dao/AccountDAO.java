package spring.dao;

import java.util.List;


import spring.entity.Accounts;


public interface AccountDAO {
	public List<Accounts> findAllAcc(Integer offset, Integer maxResult) ;
	public Long getTotalAccounts();
	public Accounts findByUserName(String userName);
	public Accounts createAcc(Accounts user);
	public void update(Accounts user);
	public Accounts delete(String userId);
}	
