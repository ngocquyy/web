package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.ContactDAO;
import spring.entity.Contact;

@Service
public class ContactServiceImpl implements ContactService{
	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public List<Contact> getListContact() {
		// TODO Auto-generated method stub
		return contactDAO.getListContact();
	}

	@Override
	public Contact findById(Integer contId) {
		// TODO Auto-generated method stub
		return contactDAO.findById(contId);
	}

	@Override
	public Contact insertContact(Contact ct) {
		// TODO Auto-generated method stub
		return contactDAO.insertContact(ct);
	}

	@Override
	public void updateContact(Contact ct) {
		// TODO Auto-generated method stub
		contactDAO.updateContact(ct);
	}

}
