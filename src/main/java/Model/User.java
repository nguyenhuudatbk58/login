package Model;

public class User {
	private int id;
	private String fullName;
	private String email;
	private String address;
	private String city;
	private String country;
	private String userName;
	private String password;

	public User(String fullName, String email, String address, String city, String country, String userName,
			String password) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.country = country;
		this.userName = userName;
		this.password = password;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
