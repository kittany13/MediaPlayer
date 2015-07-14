package com.example.mediaplayer;

import java.util.Timer;
import java.util.TimerTask;

import utils.Download;
import utils.Xml;
import android.widget.ImageView;
import data.Connect;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SongActivity extends Activity

{
	
	
	class Layout {
		public Layout() {
			img_pre = (Button) findViewById(R.id.img_pre);
			img_stop = (Button) findViewById(R.id.img_stop);
			img_pause = (Button) findViewById(R.id.img_pause);
			img_play = (Button) findViewById(R.id.img_play);
			img_next = (Button) findViewById(R.id.img_next);
			seekBar= (SeekBar)findViewById(R.id.seekBar);
			txtTime= (TextView)findViewById(R.id.txtTime);
			img_btn_song=(ImageView)findViewById(R.id.img_btn_song);

		}

		public Button img_pre;
		public Button img_stop;
		public Button img_pause;
		public Button img_play;
		public Button img_next;
		public SeekBar seekBar;
		public TextView txtTime;
		public ImageView img_btn_song;
	}

	class Events {
		
		public Events() {
			lay.img_pre.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					timer.cancel();
					newprogress=0;
					if (Connect.song_s.getSong().getNum() > 0)
					{
						int num=Connect.song_s.getSong().getNum();
						Connect.song_s.setSong(Connect.list_l.get(num-1));
						playSong(Connect.song_s.getSong().getPath(), mp);
					}
					else{
						Connect.song_s.setSong(Connect.list_l.getLast());
						playSong(Connect.song_s.getSong().getPath(), mp);
						}
					
				}

			});
			lay.img_stop.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					timer.cancel();
					mp.stop();
					mp.seekTo(0);
					lay.seekBar.setProgress(0);
					song_progress=0;
					newprogress=0;
				}

			});
			lay.img_pause.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					newprogress = song_progress*1000;
					timer.cancel();
					mp.pause();
				}

			});
			lay.img_play.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					timer.cancel();
					playSong(Connect.song_s.getSong().getPath(), mp);
				}

			});
			lay.img_next.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					playNext();
				}
			});
			lay.seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
				
				@Override
				public void onProgressChanged(SeekBar seekBar, int dur,boolean fromUser) {
					// TODO Auto-generated method stub
				}

				@Override
				public void onStartTrackingTouch(SeekBar arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {						
					if(mp.isPlaying()==true)
					{
						newprogress = seekBar.getProgress()*1000;
						timer.cancel();
						playSong(Connect.song_s.getSong().getPath(), mp);
					}
					else
					{
						newprogress = seekBar.getProgress()*1000;
						timer.cancel();
					}
				}
			});
			
		}
	}
	MediaPlayer mp = new MediaPlayer();
	int newprogress=0;
	Timer timer;
	Layout lay;
	Events e;
	int song_length;
	int song_secends;
	int song_progress;
	String name="";
	String [] track;
	String [] fraze;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_song);
		lay = new Layout();	e = new Events();
		playSong(Connect.song_s.getSong().getPath(), mp);
	}

	private void playSong(String filename, MediaPlayer mp) {
		boolean isReady = true;
		song_length =Integer.valueOf(Connect.song_s.getSong().getDuration());
		song_secends= (song_length /= 1000);
		lay.seekBar.setMax(song_secends);
		timer=new Timer();
		song_progress = 0;
		lay.seekBar.setProgress(song_progress);	
		
		mp.reset();	
		try {
			mp.setDataSource(filename);
			mp.prepare();
		} catch (Exception ex) {
			isReady = false;
		}

		if (isReady)
		{
			if(newprogress!=0)
			{
				mp.seekTo(newprogress);
				newprogress=newprogress/1000;
				lay.seekBar.setProgress(newprogress);
				song_progress=newprogress;
				newprogress=0;
			}
			mp.start();
		}
		 timer.scheduleAtFixedRate(new TimerTask() 
	     {
				@Override public void run()
				{ 
					SongActivity.this.runOnUiThread(new Runnable() 
					{
						@Override public void run() { tick_timer();}
							private void tick_timer() 
							{ 							 
								song_progress++;
								if (song_progress <= song_secends)
									 {
										 lay.seekBar.setProgress(song_progress);
									 }
									 else
									 {									
										timer.cancel();
										playNext();
									 }		 				
							}
					});
				}
	     }, 0, 1000);
		 	
		 	Download.init();   
			name = Connect.song_s.getSong().getName();
			fraze=new String[5];
			track=new String[2];
			try
			{		
				track=name.split(".mp3");
				fraze=track[0].split("-");
				lay.txtTime.setText(fraze[1]+" - BY: "+fraze[0]);
				fraze[0]=fraze[0].replaceAll(" ","%20");
				fraze[1]=fraze[1].replaceAll(" ","%20");
			    String url = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=9f0e2dde43265f68e7226584423b5ee7&artist="+fraze[0]+"&track="+fraze[1];	    
			    utils.LastFM lfm = Xml.LastFM(Download.content(url));  
		   
			    if (lfm != null)
			    {
			    	int s=lfm.track.album.images.size();
			    	if(s != 0 && lfm.track.album.images != null)
			    	{
			    		Drawable draw = Drawable.createFromStream(utils.Download.content(lfm.track.album.images.get(s-1)), "");
				    	lay.img_btn_song.setImageDrawable(draw);
			    	}
			    }
			}
			catch(Exception ex)
			{
				lay.txtTime.setText("opps!!..file name isn't valid!!");
			}
	}
	public void playNext()
	{
		timer.cancel();
		newprogress=0;
		int num=Connect.song_s.getSong().getNum();
		if ( num < Connect.list_l.size()-1)
		{
		Connect.song_s.setSong(Connect.list_l.get(num+1));
		playSong(Connect.song_s.getSong().getPath(), mp);
		}
		else
		{
		Connect.song_s.setSong(Connect.list_l.getFirst());
		playSong(Connect.song_s.getSong().getPath(), mp);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
