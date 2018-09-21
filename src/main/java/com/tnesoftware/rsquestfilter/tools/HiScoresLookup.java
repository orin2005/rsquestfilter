package com.tnesoftware.rsquestfilter.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import com.tnesoftware.rsquestfilter.Player;
import com.tnesoftware.rsquestfilter.Skill.Type;
import com.tnesoftware.rsquestfilter.impl.PlayerImpl;

public class HiScoresLookup {
	
	private static final String HISCORES_URL = "http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=";
	
	private static final Logger logger = Logger.getLogger( HiScoresLookup.class.getName() );

	public static void lookupPlayer( Player player ) throws IOException
	{
		URL url = new URL( HISCORES_URL + player.getName() );

        //make connection
        URLConnection urlc = url.openConnection();

        //get result
        BufferedReader br = new BufferedReader( new InputStreamReader( urlc.getInputStream() ) );
        String l = null;
        int i = 0;
        while ( ( l = br.readLine() ) != null && i < 24  ) {
            player.setSkill( i++, l );
        }
        player.setSkill( Type.Combat,player.calculateCombat(),0,0 );
        player.calculateQuestPoints();
        br.close();
	}

}
