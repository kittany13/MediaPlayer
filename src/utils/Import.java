package utils;

import java.util.LinkedList;

import data.Song;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class Import {
	public static LinkedList<Song> songs(ContentResolver resolver) {
				LinkedList<Song> list = songsFrom(
				android.provider.MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
				resolver);

		list.addAll(songsFrom(
				android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				resolver));

		return list;
	}

	private static LinkedList<Song> songsFrom(Uri queryUri,
			ContentResolver resolver) {
		Cursor cursor = resolver.query(queryUri, null, null, null, null);
		LinkedList<Song> list = new LinkedList<Song>();

		if (cursor == null)
			return list;

		if (cursor.moveToFirst()) {
			int count=0;
			do {
				String name = cursor
						.getString(cursor
								.getColumnIndex(android.provider.MediaStore.Audio.Media.DISPLAY_NAME));
				String path = cursor
						.getString(cursor
								.getColumnIndex(android.provider.MediaStore.Audio.Media.DATA));	
				String duration = cursor
						.getString(cursor
								.getColumnIndex(android.provider.MediaStore.Audio.Media.DURATION));	

				if (path.endsWith("mp3")) {
					Song song = new Song(name, path,duration, count);

					list.add(song);
				}
				count++;
			} while (cursor.moveToNext());

		}
		cursor.close();

		return list;
	}
	public static Drawable pictDownload(Song song)
	{
		Drawable draw= null;
		String name = song.getName();
		String [] fraze=new String[5];
		String [] track=new String[2];
				
			track=name.split(".mp3");
			fraze=track[0].split("-");
			fraze[0]=fraze[0].replaceAll(" ","%20");
			fraze[1]=fraze[1].replaceAll(" ","%20");
		    String url = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=9f0e2dde43265f68e7226584423b5ee7&artist="+fraze[0]+"&track="+fraze[1];	    
		    utils.LastFM lfm = Xml.LastFM(Download.content(url));  
		    if (lfm != null)
		    {	    	
		    		draw = Drawable.createFromStream(utils.Download.content(lfm.track.album.images.get(3)), "");
		    }	
		return draw;
	}
/*
 Clients clients = new Clients();
        
        clients.list.add(new Person(43, "david cohne", 29, true));
        clients.list.add(new Person(4353, "dana jamili", 37, false));
        
        //Person p1 = new Person(43, "david cohne", 29, true);
        //String text = "whats up???";
        
        
       
        
        
        Clients data = (Clients)open("test.txt");
        
        save("test.txt", clients);
    }
    
    private Object open(String filename)
    {
    	 Object data = null;
    	 
    	 try
    	 {
    		 FileInputStream stream = openFileInput(filename);
        	 ObjectInputStream is = new ObjectInputStream(stream);
        	 data = is.readObject();
        	 is.close();
		     stream.close();
    	 }
    	 catch(Exception e)
    	 {
    		 String msg = e.getMessage();
    	 }
    	 
    	 /*
    	 FileInputStream stream = null;
    	 ObjectInputStream is = null;
    	 
    	 try
         {
 	        stream = openFileInput(filename);
 	        is = new ObjectInputStream(stream);
 	        data = is.readObject();
         }
         catch(Exception e)
         {
         	
         }
    	 
    	 try
    	 {
	    	 if (is != null) is.close();
		     if (stream != null) stream.close();
    	 }
    	 catch(Exception e)
    	 {
    		 
    	 }
    	 */
    /*	 return data;
    }
    
    private void save(String filename, Object data)
    {
    	try
        {
	        FileOutputStream stream = openFileOutput(filename, Context.MODE_PRIVATE);
	        ObjectOutputStream os = new ObjectOutputStream(stream);
	        os.writeObject(data);
	        //stream.write(text.getBytes());
	        os.close();
	        stream.close();
        }
        catch(Exception e)
        {
        	
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

 */

}
