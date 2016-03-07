package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Model.User;
import Service.UserService;

@Controller
public class loginController {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("user")
	public User putUser() {
		return new User();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLoginView() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userAuthenticate(@RequestParam("username") String userName, @RequestParam("password") String password,
			RedirectAttributes redirectAttribute) {

		if (userService.isUserCorrect(userName, password) == false) {
			redirectAttribute.addFlashAttribute("message", "Account is incorrect.");
			return "redirect:/";
		} else {
			return "index";
		}

	}
}
