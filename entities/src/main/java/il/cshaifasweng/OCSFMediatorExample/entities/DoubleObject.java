package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.List;


import java.io.Serializable;
import java.util.List;
public class DoubleObject  implements Serializable{
	 
		private static final long serialVersionUID = 1L;
		String msg;
		link _link;
		Ticket tickets;
		Package pack;
	

		public DoubleObject(String msg, link links,Ticket ticks,Package pack) {
			this.msg = msg;
			this._link = links;
			this.tickets=ticks;
			this.pack=pack;
			
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public link getlinks() {
			return _link;
		}
		public void setlinks(link links) {
			this._link = links;
		}
		public Ticket gettickets(){
			return tickets;
		}
		public void settickets(Ticket ticket) {
			this.tickets=ticket;
		}
		public void setpackage(Package packe) {
			this.pack=packe;
		}
		public Package getPackage(){
			return pack;
		}
		
}
