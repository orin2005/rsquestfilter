package com.tnesoftware.rsquestfilter;

import com.tnesoftware.rsquestfilter.impl.PlayerImpl;
import com.tnesoftware.rsquestfilter.tools.HiScoresLookup;

public class HiScoresLookup_Test {

	public static void main(String[] args)
	{
		Player player = null;
		try
		{
			//When creating a new player, it looks up the player in constructor.
			player = new PlayerImpl( "gavo316" );
		}
		catch( Throwable x )
		{
			x.printStackTrace();
		}
		
		player.getSkills().stream().forEach( System.out::println );
	}

}
