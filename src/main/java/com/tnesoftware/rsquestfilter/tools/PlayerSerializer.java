package com.tnesoftware.rsquestfilter.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.tnesoftware.rsquestfilter.Player;

public class PlayerSerializer 
{

	private PlayerSerializer()
	{
		//Don't create an object of this, just use static methods.
	}
	
	public static void savePlayer( Player player )
	{
		if( player == null )
		{
			System.out.println( "Cannot save player, player passed in is null" );
			return;
		}
		if( canLoadPlayer( player.getName() ) )
		{
			//try to delete if the file exists already, we need to create the new file.
			new File( player.getName().toLowerCase().trim() + ".save" ).delete();
		}
		
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try
		{
			fileOut = new FileOutputStream( player.getName().toLowerCase().trim() + ".save" );
			out = new ObjectOutputStream( fileOut );
			out.writeObject(player);
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			FileUtils.close( fileOut );
			FileUtils.close( out );
		}
		
	}
	
	public static Player loadPlayer( String playerName ) throws IOException, ClassNotFoundException
	{
		Player player = null;
		
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		try
		{
			fileIn = new FileInputStream( playerName.toLowerCase().trim() + ".save" );
			in = new ObjectInputStream( fileIn );
			player = (Player)in.readObject();
		}
		finally
		{
			FileUtils.close( fileIn );
			FileUtils.close( in );
		}
		return player;
	}
	
	public static boolean canLoadPlayer( String playerName )
	{
		return new File( playerName.toLowerCase().trim() + ".save" ).exists();
	}
	
	

}
