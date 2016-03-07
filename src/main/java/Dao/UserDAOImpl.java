package Dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import Model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	private SessionFactory factory;
	private Session session;
	private Transaction ts;

	UserDAOImpl() {
		try {
			factory = new Configuration().configure().buildSessionFactory();

		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
	}

	public String getPassword(String userName) {
		session = factory.openSession();
		ts = session.beginTransaction();
		String hql = "select password from User where userName= ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userName);
		String password = (String) query.uniqueResult();

		return password;
	}

	public void saveOrUpdate(User user) {
		session = factory.openSession();
		ts = session.beginTransaction();

		session.saveOrUpdate(user);
		ts.commit();
		session.close();

	}

	public ArrayList<String> getUserNames() {
		session = factory.openSession();
		ts = session.beginTransaction();
		String hql = "select userName from User";
		ArrayList<String> userNames = (ArrayList<String>) session.createQuery(hql).list();
		return userNames;
	}

	public ArrayList<User> getUsers() {
		session = factory.openSession();
		ts = session.beginTransaction();
		String hql = "FROM User";
		ArrayList<User> users = (ArrayList<User>) session.createQuery(hql).list();
		return users;
	}

	public User getUserByName(String userName) {
		session = factory.openSession();
		ts = session.beginTransaction();
		String hql = "FROM User where userName=? ";
		Query query = session.createQuery(hql).setParameter(0, userName);
		User user = (User) query.uniqueResult();
		return user;
	}

	public void deleteUser(String userName) {
		session = factory.openSession();
		ts = session.beginTransaction();
		String hql = "delete from User where userName= ?";
		Query query = session.createQuery(hql).setParameter(0, userName);
		query.executeUpdate();
		ts.commit();
	}

	public boolean wasUserExisted(String userName) {
		ArrayList<String> userNames = getUserNames();
		if (userNames.contains(userName)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isUserCorrect(String userName, String password) {
		String pw = getPassword(userName);
		if (pw == null) {
			// tai khoan khong ton tai.
			return false;
		} else if (pw.equals(password) == false) {
			// mat khau nhap khong chinh xac
			return false;
		} else
			return true;
	}

	public User getUserById(int id) {
		session = factory.openSession();
		ts = session.beginTransaction();
		String hql = "from User where id=?";
		Query query = session.createQuery(hql).setParameter(0, id);
		User user = (User) query.uniqueResult();
		return user;
	}

}
