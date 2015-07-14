package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.os.StrictMode;

public class Download 
{
	public static void init()
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}
	
	public static InputStream content(String url)
	{
		try
		{
			return new URL(url).openStream();
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public static String from(String url)
	{
		String value;
		try
		{
			InputStream is = content(url);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			StringBuilder builder = new StringBuilder();
			String line = reader.readLine();

			while (line != null)
			{
				builder.append(line);
				
				line = reader.readLine();
			} 
			
		    value = builder.toString();
		    
		}
		catch(Exception ex)
		{
			value = "";
		}
		
		return value;
	}
	
}
