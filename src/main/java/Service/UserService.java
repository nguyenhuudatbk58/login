package Service;

import java.util.ArrayList;

import Model.User;

public interface UserService {
	User getUserById(int id);

	String getPassword(String userName);

	void saveOrUpdate(User user);

	ArrayList<String> getUserNames();
	
	ArrayList<User> getUsers();
	
	User getUserByName(String userName);
	
	void deleteUser(String userName);
	
	boolean wasUserExisted(String userName);
	
	boolean isUserCorrect(String userName, String password);
	

}
