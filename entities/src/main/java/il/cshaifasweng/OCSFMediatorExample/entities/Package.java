package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "links")
public class Package {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int package_id;
	private int number_of_ticekts;
	//private User user;

	private List<Ticket> myTickets;
	public Package() {
		
	}
	public Package (List<Ticket> mov) {
		this.myTickets=mov;
		this.number_of_ticekts=20;
		
	}
	
	//public void set_user(User _user) {
	//	this.user=_user;
	//}
	
	
	
}
