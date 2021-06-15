package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.List;

public class UserFoundEvent {

	int userRole;
	String userName;

	public UserFoundEvent(String myMsg, List<String> list) {
		this.userRole = Integer.parseInt(myMsg.substring(11));
		this.userName = list.get(0);
	}

	public int getRole() {
		return this.userRole;
	}

	public String getUserName() {
		return this.userName;
	}
}
