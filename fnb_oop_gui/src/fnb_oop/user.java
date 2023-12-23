package fnb_oop;

public class user {
	private String userId,created,username,password,name,email,address;
	
	public user(String userId,String created,String username,String password,String name,String email,String address) {
		this.userId = userId;
		this.created = created;		
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
	}
	
	public String getUserId() {
		return userId;
	}
	public String getCreated() {
		return created;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
}
