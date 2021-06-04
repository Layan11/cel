package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.List;

public class gotMovieActorsEvent {
	List<String> actors;

	public gotMovieActorsEvent(List<String> list) {
		this.actors = list;
	}

	public List<String> getMovieActors() {
		return this.actors;
	}
}
