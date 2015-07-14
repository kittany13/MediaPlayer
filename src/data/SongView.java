package data;

import android.content.Context;
import android.widget.TextView;

public class SongView extends TextView {
	public SongView(Context context) {
		super(context);
	}

	private Song song;
	private int temp;
	
	
	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
		this.setText(song.getName());
	}
	

}
