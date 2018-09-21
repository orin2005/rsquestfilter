package com.tnesoftware.rsquestfilter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.tnesoftware.rsquestfilter.Item;
import com.tnesoftware.rsquestfilter.Quest;
import com.tnesoftware.rsquestfilter.Skill;
import com.tnesoftware.rsquestfilter.Skill.Type;

public class QuestImpl implements Quest
{
	
	private List<Quest> questRequirements;
	private List<Item> itemRequirements;
	private List<Skill> skillRequirements;
	
	private String name;
	private int questPoints;
	private boolean complete;
	private String urlToRuneHQ;
	
	private Length length;
	private Difficulty difficulty;

	/**
	 * Created a new quest based on name, number of quest points, length, and difficulty of the quest.
	 * @param name
	 * @param questPoints
	 * @param length
	 * @param difficulty
	 */
	public QuestImpl( String name, int questPoints, Length length, Difficulty difficulty, String url ) 
	{
		this.name = name;
		this.questPoints = questPoints;
		this.length = length;
		this.difficulty = difficulty;
		this.complete = false;
                this.urlToRuneHQ = url;
		
		questRequirements = new ArrayList<Quest>();
		itemRequirements = new ArrayList<Item>();
		skillRequirements = new ArrayList<Skill>();
		
	}
	
	/**
	 * This constructor is just rearranged arguments from the above constructor. For ease of use halfway through
	 * making all the quest objects.
	 */
	public QuestImpl( String name, Difficulty difficulty, Length length, int questPoints, String url )
	{
		this( name, questPoints, length, difficulty, url );
	}
	
	/**
	 * copy constructor 
	 * @param quest
	 */
	public QuestImpl( Quest quest )
	{
		this( quest.getName(), quest.getQuestPoints(), quest.getLength(), quest.getDifficulty(), quest.getURLToRuneHQQuestGuide() );
		
		questRequirements = quest.getQuestRequirements();
		itemRequirements = quest.getItemRequirements();
		skillRequirements = quest.getSkillRequirements();
	}

	public List<Quest> getQuestRequirements() 
	{
		return questRequirements;
	}

	public List<Item> getItemRequirements() 
	{
		return itemRequirements;
	}

	public List<Skill> getSkillRequirements() 
	{
		return skillRequirements;
	}

	public boolean isComplete() 
	{
		return complete;
	}

	public String getURLToRuneHQQuestGuide() {
		// TODO Auto-generated method stub
		return urlToRuneHQ;
	}

	/**
	 * Sets the quest requirements of this quest by passing in Quest objects.
	 * 
	 * 
	 * I.E. quest.setQuestRequirements( quest1, quest2, quest3 );
	 * 
	 * The above sets the requirements of quest to quest1, quest2, and quest3.
	 */
	public void setQuestRequirements( Quest... quests ) 
	{
		Arrays.stream( quests ).forEach( questRequirements::add );
		
	}

	/**
	 * Sets the item requirements of this quest by passing in Item objects.
	 */
	public void setItemRequirements( Item... items ) 
	{
		Arrays.stream( items ).forEach( itemRequirements::add );
	}

	/**
	 * Sets the skill requirements of this quest by passing in Skill objects.
	 */
	public void setSkillRequirements( Skill... skills ) 
	{
		Arrays.stream( skills ).forEach( skillRequirements::add );
		
	}
	


	/**
	 * Name of this quest. This is unique to each quest.
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * The Length enum value of this quest.
	 */
	@Override
	public Length getLength() 
	{
		return length;
	}
	


	/**
	 * Number of quest points this quest is worth.
	 */
	@Override
	public int getQuestPoints() {
		return questPoints;
	}

	/**
	 * Gets the Difficulty enum value of this quest.
	 */
	@Override
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	/**
	 * A quest is considered equal if their names are the same. Compare based on this
	 * and return a boolean value.
	 */
	@Override
	public boolean isEqual( Quest quest )
	{
		return quest != null && name != null && name.equals( quest.getName() );
	}

	@Override
	public void setComplete( boolean complete ) {
		this.complete = complete;
	}
	
	public void setSkillRequirements( Type type, int level )
	{
		skillRequirements.add( new SkillRequirement(type, level));
	}

	@Override
	public int compareTo( Quest q ) 
	{
		return name.compareTo( q.getName() );
	}
	
	/**
	 * String representation of this quest.
	 */
	@Override
	public String toString()
	{
		StringBuilder questString = new StringBuilder();
		questString.append( "Quest Requirements:\n\t");
		if( questRequirements.isEmpty() )
			questString.append( "None\n" );
		else
		{
			questRequirements.stream().forEach( q -> questString.append( q.getName() + "\n\t" ) );
		}
		questString.append( "\n" ).append( "Skill Requirements:\n\t" );
		if( skillRequirements.isEmpty() )
			questString.append( "None" );
		else
		{
			skillRequirements.stream().forEach( skill -> questString.append( skill.getType().name() + " ("+ skill.getLevel() + ")" ).append("\n\t") );
		}
		
		return questString.toString();
	}

}
