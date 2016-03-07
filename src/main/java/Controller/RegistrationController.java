package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Model.User;
import Service.UserService;
import Validation.UserFormValidator;

@Controller
public class RegistrationController {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setIgnoreUnknownFields(true);
		binder.setIgnoreInvalidFields(true);
	}

	@ModelAttribute("user")
	public User putUser() {
		return new User();
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user, Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "login";
		}
		if (userService.wasUserExisted(user.getUserName()) == true) {
			model.addAttribute("msg", "User name has been existed. ");
			return "login";
		} else {
			userService.saveOrUpdate(user);
			return "index";
		}
	}
}
