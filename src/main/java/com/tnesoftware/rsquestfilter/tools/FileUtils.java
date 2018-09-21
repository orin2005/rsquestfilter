package com.tnesoftware.rsquestfilter.tools;

import java.io.Closeable;

public class FileUtils {

	private FileUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static void close( Closeable c )
	{
		if( c == null )
			return;
		try
		{
			c.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

}
