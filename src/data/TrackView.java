package data;



import com.example.mediaplayer.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TrackView extends LinearLayout
{
	/*class Layout
	{
		public Layout()
		{
			layoutTrack = (LinearLayout)findViewById(R.id.layoutTrack);
			txtView = (TextView)findViewById(R.id.txtView);
			imgView = (ImageView)findViewById(R.id.imgView);
		}
		LinearLayout layoutTrack;
		TextView txtView;
		ImageView imgView;
	}
	class Events
	{
		public Events()
		{
			
		}
	}
	public Layout l;
	public Events e;*/
	LinearLayout layoutTrack;
	TextView txtView;
	ImageView imgView;
	public TrackView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_track, this);
		//l = new Layout(); e = new Events();
		
		layoutTrack = (LinearLayout)findViewById(R.id.layoutTrack);
		txtView = (TextView)findViewById(R.id.txtView);
		imgView = (ImageView)findViewById(R.id.imgView);
		    
		/*String url = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=9f0e2dde43265f68e7226584423b5ee7&artist=avicii&track=Wake%20Me%20Up";	    
		    utils.LastFM lfm = Xml.LastFM(Download.content(url));  
		    if (lfm != null)
		    {	    	
		    		Drawable draw = Drawable.createFromStream(utils.Download.content(lfm.track.album.images.get(0)), "");
		    		setimg(draw);
		    }
		*/
		
		
	}	
	
	public void settxt(String s)
	{
		txtView.setText(s);
	}
	public String gettxt()
	{
		return txtView.getText().toString();
	}
	
	public void setimg(Drawable img)
	{
		imgView.setImageDrawable(img);
	}
	
}
