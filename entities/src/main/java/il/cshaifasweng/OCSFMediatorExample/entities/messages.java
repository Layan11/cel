package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

	@Entity
	@Table(name = "messages")
	public class messages implements Serializable {
		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String fromName;   //the one that send the message
		private String MSGcontext;
		private LocalDate date;
		
		//@ManyToOne
		private String user;  //the user is the one that will see the message

		public messages() {

		}

		public messages(String fromName, String mSGcontext, String user) {
			super();
			this.fromName = fromName;
			MSGcontext = mSGcontext;
			this.user = user;
			setDate(LocalDate.now());
		}

		public String getId() {
			String s=Integer.toString(id);
			return s;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFromName() {
			return fromName;
		}

		public void setFromName(String fromName) {
			this.fromName = fromName;
		}

		public String getMSGcontext() {
			return MSGcontext;
		}

		public void setMSGcontext(String mSGcontext) {
			MSGcontext = mSGcontext;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}
}
