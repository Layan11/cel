package il.cshaifasweng.OCSFMediatorExample.entities;

public class Employee {
	String User_Name;
	String Password;
	boolean Is_Logged_In;
	int Role; // 0 -> Network Manager , 1 -> Content Manager , 2 -> Costumer Services Employee
	public String getUser_Name() {
		return User_Name;
	}
	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean isIs_Logged_In() {
		return Is_Logged_In;
	}
	public void setIs_Logged_In(boolean is_Logged_In) {
		Is_Logged_In = is_Logged_In;
	}
	public int getRole() {
		return Role;
	}
	public void setRole(int role) {
		Role = role;
	}
	public Employee(String user_Name, String password, boolean is_Logged_In, int role) {
		super();
		User_Name = user_Name;
		Password = password;
		Is_Logged_In = is_Logged_In;
		Role = role;
	}
	
	

}
