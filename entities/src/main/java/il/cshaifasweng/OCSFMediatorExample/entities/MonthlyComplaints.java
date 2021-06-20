package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "MonthlyComplaints")
public class MonthlyComplaints implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Cid;
	public int[] complaintspermonth;
	
	public MonthlyComplaints()
	{
		super();
		this.complaintspermonth = new int[32];
		
	}
	
	public MonthlyComplaints(int[] complaintspermonth)
	{
		super();
		this.complaintspermonth = complaintspermonth;
	}

	public int[] getComplaintspermonth() {
		return complaintspermonth;
	}

	public void setComplaintspermonth(int[] complaintspermonth) {
		this.complaintspermonth = complaintspermonth;
	}
	
	

}
