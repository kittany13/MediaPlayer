package utils;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class LastFM 
{
	@Element(required=false)
	public Track track;
	
	@Attribute(name="status")
	public String status;
	
	@Root
	public static class Track 
	{
		@Element(required=false)
		public Album album;
		
		public static class Album
		{
			@Element
			public String artist;
			
			@Element
			public String title;
			
			@ElementList(type=String.class, entry="image", required=false, inline=true)
			public List<String> images;  
		}
	}
}
