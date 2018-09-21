package com.tnesoftware.rsquestfilter;

import com.tnesoftware.rsquestfilter.impl.PlayerImpl;
import com.tnesoftware.rsquestfilter.tools.PlayerSerializer;

public class PlayerSerializer_Test {

	public static void main(String[] args)
	{
		Player player = new PlayerImpl("Elite orin");
		
		PlayerSerializer.savePlayer( player );
		
		player = null;
		
		if( PlayerSerializer.canLoadPlayer( "Elite orin" ) )
		{
			try
			{
				player = PlayerSerializer.loadPlayer( "Elite orin" );
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			System.out.println( player.getName() );
		}
	} 

}
