package utils;

import java.io.InputStream;

import org.simpleframework.xml.core.Persister;


public class Xml 
{
	/*public static data.Demo demo(InputStream stream)
	{
		data.Demo demo = new Demo();
		
		if (stream == null) return demo;
		
		try
		{
			Serializer serializer = new Persister();
			demo = serializer.read(utils.LastFM.class, stream);
		}
		catch(Exception ex)
		{
			
		}
		
		return demo;
	}*/
	
	public static utils.LastFM LastFM(InputStream stream)
	{
		if (stream == null) return null;

		try
		{
			return new Persister().read(utils.LastFM.class, stream, false);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	/*public static utils.Currency Currency(InputStream stream)
	{
		if (stream == null) return null;

		try
		{
			return new Persister().read(utils.Currency.class, stream, false);
		}
		catch(Exception ex)
		{
			return null;
		}
	}*/
}
