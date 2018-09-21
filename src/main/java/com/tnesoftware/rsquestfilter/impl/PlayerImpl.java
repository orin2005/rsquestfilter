package com.tnesoftware.rsquestfilter.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tnesoftware.rsquestfilter.Player;
import com.tnesoftware.rsquestfilter.Quest;
import com.tnesoftware.rsquestfilter.Quest.Manager;
import com.tnesoftware.rsquestfilter.Skill;
import com.tnesoftware.rsquestfilter.Skill.Type;
import com.tnesoftware.rsquestfilter.tools.HiScoresLookup;
import com.tnesoftware.rsquestfilter.tools.PlayerSerializer;

public class PlayerImpl implements Player
{
	
	private String name;
	private List<Skill> skills;
	private Quest.Manager questManager;
	
	private final static Logger logger = Logger.getLogger( PlayerImpl.class.getName() );

	/**
	 * If just the name is passed in, we want to pass in true to let the player be looked up.
	 * @param name
	 * @throws Throwable
	 */
	public PlayerImpl( String name )
	{
		this( name, true );
	}
	
	public PlayerImpl( String name, boolean lookup ) 
	{
		this.name = name;
		skills = new ArrayList<Skill>();
		questManager = new QuestManager( this );
		
		//Initialize the player by looking him/her up. If we can't lookup we should probably throw an error.
		if( lookup )
		{
			try {
				HiScoresLookup.lookupPlayer( this );
			} 
                        catch ( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
                                System.out.println("Could not find player");
			}
		}
		
		
		try {
                    skills.add( new SkillImpl( Type.Quest , 0) );
                    skills.add( new SkillImpl( Type.Combat, calculateCombat() ) );
                    calculateQuestPoints();
                    PlayerSerializer.savePlayer( this );
                }
                catch(Exception ex) {
                    System.out.println("Could not load player");
                }
	}

	@Override
	public List<Skill> getSkills() {
		return skills;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * This gets called from the hiScores API HiScoresLookup class.
	 */
	@Override
	public void setSkill( final int skillNum, String line ) {
		int level = parseLevel( line );
		int rank = parseRank( line );
		int experience = parseExp( line );
		setSkill( Skill.Type.values()[skillNum], level, experience, rank );
	}


	@Override
	public void setSkill( int skillNum, int level, int experience, int rank ) 
	{
		setSkill( Skill.Type.values()[skillNum], level, experience, rank );
		
	}

	@Override
	public void setSkill( Type skillType, int level, int experience, int rank ) 
	{
		if( !skills.stream().map( Skill::getType ).anyMatch( s -> s.equals( skillType ) ) )
		{
			skills.add( new SkillImpl( skillType, level, experience, rank ) );
		}
		else
		{
			logger.log( Level.INFO, "This Player already has this skill" );
			throw new IllegalArgumentException( "Player already had " + skillType.name() + " value set");
		}
		
	}
	
	public Skill getSkill( Type skillType )
	{
		try
		{
			return getSkills().stream().filter( s -> s.getType().equals( skillType ) ).findFirst().get();
		}
		catch( NoSuchElementException nsee )
		{
			return null;
		}
	}
	
	@Override
	public void updateSkill( Type skillType, int level, int experience, int rank )
	{
		if( !skills.stream().map( Skill::getType ).anyMatch( s -> s.equals( skillType ) ) )
		{
			logger.log( Level.INFO, "This player does not have this skill yet" );
			throw new IllegalArgumentException( "Cannot update " + skillType.name() + ". Value not Set yet.");
		}
		else
		{
			Skill skill = skills.stream().filter( s -> s.getType().equals( skillType ) ).iterator().next();
			
			skill.setLevel( level );
			skill.setExperience( experience );
			skill.setRank( rank );
		}
	}
	
	@Override
	public void updateAllSkills()
	{
		skills = new ArrayList<Skill>();
		try {
			HiScoresLookup.lookupPlayer(this);
		} catch ( Exception e ) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.log( Level.SEVERE, "Severe error while trying to lookup player" );
		}
	}
	
	
	private int parseLevel( String line )
	{
		
		return Integer.parseInt( line.split(",")[1] );
	}
	
	private int parseRank( String line )
	{
		return Integer.parseInt( line.split(",")[0] );
	}
	
	private int parseExp( String line )
	{
		return Integer.parseInt( line.split(",")[2] );
	}

	@Override
	public List<Quest> getAllQuests() {
		return questManager.getAllQuests();
	}

	@Override
	public List<Quest> getCompletedQuests() {
		return questManager.getQuestsCompleted();
	}

	@Override
	public boolean canComplete( Quest quest ) 
	{
		boolean canCompleteQuest = true;
		
		if( quest.getQuestRequirements().isEmpty() && quest.getSkillRequirements().isEmpty() )
			return true;
		
		if( quest.getName().equals("Black Knights' Fortress"))
		{
			System.out.println("");
		}
		
		if( !quest.getQuestRequirements().isEmpty() )
		{
			for( Quest q : quest.getQuestRequirements() )
			{
				canCompleteQuest = verifyQuestComplete( q );
				if( !canCompleteQuest )
					return canCompleteQuest;
			}
		}
		
		if( !quest.getSkillRequirements().isEmpty() )
		{
			for( Skill s : quest.getSkillRequirements() )
			{
				canCompleteQuest = verifySkillLevel( s );
				if( !canCompleteQuest )
					return canCompleteQuest;
			}
		}
		
		return canCompleteQuest;
	}
	
	private boolean verifyQuestComplete( Quest quest )
	{
		return questManager.getQuestsCompleted().stream().filter( q -> q.isEqual( quest ) ).count() != 0;
	}
	
	private boolean verifySkillLevel( Skill skill )
	{
		Skill mySkill = getSkill( skill.getType() );
		
		return mySkill.greaterThanOrEqualTo( skill );
	}

	@Override
	public Manager getQuestManager() {
		return questManager;
	}

	@Override
	public int calculateCombat() 
	{
		float base = ((getSkill(Type.Prayer).getLevel()/2) + getSkill(Type.Hitpoints).getLevel() + getSkill(Type.Defence).getLevel())/(float)4;
		float strAtt = (getSkill(Type.Strength).getLevel() + getSkill(Type.Attack).getLevel()) * 0.325f;
		
		float mage = ((getSkill(Type.Magic).getLevel()/2) + getSkill(Type.Magic).getLevel()) * 0.325f;
		float range = ((getSkill(Type.Ranged).getLevel()/2) + getSkill(Type.Ranged).getLevel()) * 0.325f;
		return (int) (base+(Math.max(strAtt, Math.max(mage, range))));
	}

	@Override
	public int getQuestPoints() {
		return questManager.getQuestsCompleted().stream().mapToInt( Quest::getQuestPoints ).sum();
	}

	@Override
	public void calculateQuestPoints() {
		int questPoints = getQuestPoints();
		logger.log( Level.INFO, "Updating player's questpoints to " + questPoints );
		
		if( getSkill(Type.Quest) == null )
		{
			setSkill( Type.Quest, questPoints, 0, 0 );
		}
		
		updateSkill( Type.Quest, questPoints, 0, 0 );
		
	}

}
