package com.tnesoftware.rsquestfilter;

import com.tnesoftware.rsquestfilter.impl.PlayerImpl;

public class Quest_Test {

	public static void main(String[] args) throws Throwable 
	{
		Player player = new PlayerImpl( "Elite Orin" );

		player.getQuestManager().getAllQuests().stream().forEach(System.out::println);
	}

}
