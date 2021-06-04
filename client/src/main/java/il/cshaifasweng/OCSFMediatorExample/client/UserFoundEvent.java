package il.cshaifasweng.OCSFMediatorExample.client;

public class UserFoundEvent {

	int userRole;

	public UserFoundEvent(String myMsg) {
		this.userRole = Integer.parseInt(myMsg.substring(11));
	}

	public int getRole() {
		return this.userRole;
	}
}
