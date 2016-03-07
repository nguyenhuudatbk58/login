package Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Model.User;
import Service.UserService;
import Validation.UserFormValidator;

@Controller
@RequestMapping("/userManagement")
public class userManagementController {
	private UserService userService;

	@Autowired
	UserFormValidator userFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}

	@ModelAttribute("user")
	public User putUser() {
		return new User();
	}

	@ModelAttribute("editedUser")
	public User putEditedUser() {
		return new User();
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showUserListForm(Model model) {
		ArrayList<User> users = userService.getUsers();
		model.addAttribute("users", users);
		return "userList";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String showAddUserForm() {
		return "addUserForm";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUserHandle(@ModelAttribute("user") @Validated User user, BindingResult result,
			RedirectAttributes redirectAttribute) {
		if (result.hasErrors()) {
			return "redirect:/userManagement/addUser";
		} else {
			if (userService.wasUserExisted(user.getUserName()) == true) {
				redirectAttribute.addFlashAttribute("msgCheckUserExist", "Tên tài khoản đã tồn tại");
				return "redirect:/userManagement/addUser";

			} else {
				userService.saveOrUpdate(user);
				return "redirect:/userManagement";
			}
		}
	}

	@RequestMapping(value = "/edit/{userName}", method = RequestMethod.GET)
	public String editUser(@PathVariable("userName") String userName, Model model) {
		User user = userService.getUserByName(userName);
		model.addAttribute("user", user);
		return "editUserForm";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String editUser(@PathVariable("id") int id, @ModelAttribute("editedUser") User user, BindingResult result,
			RedirectAttributes redirectAttribute) {
		if (result.hasErrors()) {
			return "redirect:/userManagement/edit/" + user.getUserName();
		} else {
			if (user.getUserName().equals(userService.getUserById(id).getUserName())) {
				user.setId(id);
				userService.saveOrUpdate(user);
				return "redirect:/userManagement";
			} else if (userService.wasUserExisted(user.getUserName()) == true) {
				redirectAttribute.addFlashAttribute("msgCheckUserExits", "Tên tài khoản đã tồn tại");
				return "redirect:/userManagement/edit/" + user.getUserName();
			} else {
				user.setId(id);
				userService.saveOrUpdate(user);
				return "redirect:/userManagement";
			}
		}
	}

	@RequestMapping(value = "/delete/{userName}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("userName") String userName) {
		userService.deleteUser(userName);
		return "redirect:/userManagement";
	}

}
