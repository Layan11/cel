package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	String User_Name;
	String Password;
	int role; // -1->user,0 -> Network Manager , 1 -> Content Manager , 2 -> Costumer Services
				// Employee
	boolean Is_Logged_In;
	private int packageId = -1;
	private String Payment_Method;
	
	//private List<messages> msg;
	
	public User() {

	}

	public User(String user_Name, String password) {
		super();
		this.User_Name = user_Name;
		this.Password = password;
		//msg=new ArrayList<messages>();
	}
	

	//public List<messages> getMsg() {
		//return msg;
	//}

	//public void setMsg(List<messages> msg) {
	//	this.msg = msg;
	//}

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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public boolean isIs_Logged_In() {
		return Is_Logged_In;
	}

	public void setIs_Logged_In(boolean is_Logged_In) {
		Is_Logged_In = is_Logged_In;
	}

	public int getID() {
		return id;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public void setPay(String way) {
		this.Payment_Method=way;
	}
}
