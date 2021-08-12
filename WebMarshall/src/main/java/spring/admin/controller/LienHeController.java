package spring.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.entity.Contact;
import spring.service.ContactService;

@Controller
public class LienHeController {
	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/admin/listContact")
	public String listContact(Model model) {
		model.addAttribute("listContact", contactService.getListContact());
		return "admin/listContact";
	}
	
	@GetMapping("/admin/addContact")
	public String addContact(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact", contact);
		return "/admin/addContact";
	}
	@PostMapping("/admin/addContact")
	public String addContact(@ModelAttribute("contact")Contact contact, Model model) {
		contactService.insertContact(contact);
		return "redirect:/admin/listcontact";
	}
	
	@GetMapping("/admin/editContact")
	public String preEditContact(Model model, @RequestParam("contactId")Integer contactId) {
		Contact contact = contactService.findById(contactId);
		model.addAttribute("contact", contact);
		return "admin/editContact";
	}
	
	@PostMapping("/admin/editContact")
	public String editContact(@ModelAttribute("contact")Contact contact , Model model) {
		contactService.updateContact(contact);
		return"redirect:/admin/listContact";
	}
	
	
}
