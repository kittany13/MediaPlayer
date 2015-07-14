package com.example.mediaplayer;

import java.util.LinkedList;

import utils.Import;
import data.*;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinkedList<Song> songs = Import.songs(getContentResolver());
		LinearLayout lstSongs = (LinearLayout) findViewById(R.id.lstSongs);
		fillActivity(songs, lstSongs);
	}
	private void fillActivity(final LinkedList<Song> songs, LinearLayout container) {
		for (int i = 0; i < songs.size(); i++) {
			SongView song = new SongView(this);
			song.setSong(songs.get(i));
			TrackView tv = new TrackView(this, null);
			tv.settxt(songs.get(i).getName());
			try
			{
				tv.setimg(Import.pictDownload(songs.get(i)));
			}
			catch(Exception e)
			{
				
			}
			song.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View view){
					SongView song = (SongView) view;
					Connect.song_s=song;
					Connect.list_l=songs;
					Intent intent = new Intent(MainActivity.this,SongActivity.class);
					startActivity(intent);
				}
			});
			container.addView(tv);
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
