package Dao;

public class Test {

	public static void main(String[] args) {
		UserDAO ud = new UserDAOImpl();
		String pw = ud.getPassword("user4");
		if (pw == null) {
			System.out.println("Password null");
		} else {
			System.out.println("password: " + pw);
		}
	}

}
