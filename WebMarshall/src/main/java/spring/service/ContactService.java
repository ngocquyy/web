package spring.service;

import java.util.List;

import spring.entity.Contact;

public interface ContactService {
	public List<Contact> getListContact();
	public Contact findById(Integer contId);
	public Contact insertContact(Contact ct); 
	public void updateContact(Contact ct);
	
}
