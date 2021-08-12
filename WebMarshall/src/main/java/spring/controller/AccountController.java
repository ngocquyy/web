package spring.controller;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.bean.MailInfo;
import spring.entity.Accounts;
import spring.service.AccountService;
import spring.service.MailService;

@Controller
@Transactional
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private MailService mailService;
	@Autowired
	private HttpServletRequest request;
	


	@RequestMapping(value = { "/account/login" }, method = RequestMethod.GET)
	public String login(Model model) {
		
		return "account/login";
	}

	@RequestMapping(value = { "/account/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.isEnabled());

		model.addAttribute("userDetails", userDetails);
		
		return "account/accountInfo";
	}
//	@GetMapping("/account/login1")
//	public String login(Model model) {
//		Cookie ckusername = cookie.read("username");
//		Cookie ckpassword = cookie.read("password");
//		if (ckusername != null ) {
//			String uname = ckusername.getValue();
//			String upass = ckpassword.getValue();
//			
//			model.addAttribute("uname", uname);
//			model.addAttribute("upass", upass);
//		}
//		return "account/login1";
//	}
//	
//	@PostMapping("/account/login1")
//	public String login(Model model, @RequestParam("userName")String userName,
//						@RequestParam("password")String password, @RequestParam(value = "remember", defaultValue = "true")boolean remember) {
//		Account user = accountDAO.findByUserName(userName);
//		if (user == null) {
//			model.addAttribute("notification", "Sai tên đăng nhập");
//		} else if(!password.equals(user.getPassword())){
//			model.addAttribute("notification", "Sai mật khẩu");
//		}else if (!user.isActive()) {
//			model.addAttribute("notification", "Tài khoản chưa kích hoạt");
//		}else {
//			model.addAttribute("notification", "Đăng nhập thành công");
//			session.setAttribute("user", user);
//			if (remember == true) {
//				cookie.create("username", user.getUserName(), 30);
//				cookie.create("password", user.getUserName(), 30);
//			} else {
//				cookie.delete("username");
//				cookie.delete("password");
//			}
//		}
//		
//		return "account/login1";
//	}
	
	@GetMapping("/account/register")
	public String register(Model model) {
		Accounts user = new Accounts();
		
		model.addAttribute("user", user);
		
		return "account/register";
	}

	@PostMapping("/account/register")
	public String register(@Validated @Valid @ModelAttribute("user") Accounts user, Model model, BindingResult result) throws MessagingException {
		if (result.hasErrors()) {
			model.addAttribute("notification", "Có lỗi xảy ra. Vui lòng kiểm tra lại !!");
			return "account/register";
		}else {
			Accounts user2 = accountService.findByUserName(user.getUserName());
			if (user2 != null) {
				model.addAttribute("notification", "Tên tài khoản đã được dùng !!");
				return "account/register";
			} 
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passEncoder = encoder.encode(user.getPassword());
		user.setPassword(passEncoder);
		user.setActive(false);
		user.setUserRole("ROLE_USER");
		accountService.createAcc(user);
		model.addAttribute("notification", "register ok");

		String from = "webstiequy@gmail.com";
		String to = user.getEmail();
		String subject = "Welcome";
		String url = request.getRequestURL().toString().replace("register", "activate/" + user.getUserName());
		String body = "Click <a href='" + url + "'> Activate </a>";

		MailInfo mail = new MailInfo(from, to, subject, body);
		mailService.send(mail);
		return "account/register";
	}

	@GetMapping("/account/activate/{userName}")
	public String active(@PathVariable("userName") String userName) {
		Accounts user = accountService.findByUserName(userName);
		user.setActive(true);
		accountService.update(user);
		return "redirect:/account/login";
	}

	@GetMapping("/account/forgot")
	public String forgot(Model model) {
		return "account/forgot";
	}

	@PostMapping("/account/forgot")
	public String forgot(@RequestParam("userName") String userName, @RequestParam("email") String email, Model model)
			throws MessagingException {
		Accounts user = accountService.findByUserName(userName);
		if (user == null) {
			model.addAttribute("notification", "Invalid username");
		} else if (!email.equals(user.getEmail())) {
			model.addAttribute("notification", "Invalid email address");
		} else {
			String from = "webstiequy@gmail.com";
			String to = user.getEmail();
			String subject = "Reset password";
			String body = "Your password is" + user.getPassword();
			MailInfo mail = new MailInfo(from, to, subject, body);
			mailService.send(mail);
			model.addAttribute("notification", "Password was sent to your email");
		}

		return "redirect:/account/login";
	}

	@GetMapping("/account/change")
	public String change(Model model) {
		return "account/change";
	}

	@PostMapping("/account/change")
	public String change(Model model, @RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam("password1") String password1,
			@RequestParam("password2") String password2) {
		
		if (!password1.equals(password2)) {
			model.addAttribute("notification", "password not match");
		} else {
			Accounts user = accountService.findByUserName(userName);
			if (user == null) {
				model.addAttribute("notification", "Invalid username");
			} else if (!password.equals(user.getPassword())) {
				model.addAttribute("notification", "Invalid password");
			} else {
				user.setPassword(password1);
				accountService.update(user);
				model.addAttribute("notification", "pas"
						+ "sword changed");
			}
		}
		return "redirect:/account/login";
	}
	
	@GetMapping("/account/edit")
	public String preEdit(Model model) {
		//phai lay uername tu session ra
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Accounts user = accountService.findByUserName(userDetails.getUsername());
		
		model.addAttribute("user", user);
		return "account/edit";
	}
	@PostMapping("/account/edit")
	public String edit(Model model, @ModelAttribute("user")Accounts user) {
		
		accountService.update(user);
		model.addAttribute("user", user);
		return "account/edit";
	}
}
