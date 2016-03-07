package Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Dao.UserDAO;
import Model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public String getPassword(String userName) {
		String password = userDAO.getPassword(userName);
		return password;
	}

	public void saveOrUpdate(User user) {
		userDAO.saveOrUpdate(user);

	}

	public ArrayList<String> getUserNames() {
		ArrayList<String> userNames = userDAO.getUserNames();
		return userNames;
	}

	public ArrayList<User> getUsers() {
		ArrayList<User> users = userDAO.getUsers();
		return users;
	}

	public User getUserByName(String userName) {
		User user = userDAO.getUserByName(userName);
		return user;
	}

	public void deleteUser(String userName) {
		userDAO.deleteUser(userName);
	}

	public boolean wasUserExisted(String userName) {
		return userDAO.wasUserExisted(userName);
	}

	public boolean isUserCorrect(String userName, String password) {
		return userDAO.isUserCorrect(userName, password);
	}

	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

}
