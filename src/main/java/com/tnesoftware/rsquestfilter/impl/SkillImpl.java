package com.tnesoftware.rsquestfilter.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tnesoftware.rsquestfilter.Skill;

/**
 * Implements the Skill interface.
 * @author Trentin Thomas
 *
 */
public class SkillImpl implements Skill
{
	
	public static final Map<Integer, Integer> levelToExperience = new HashMap<Integer,Integer>();
	
	static
	{
		levelToExperience.put( 1, 0 );
		levelToExperience.put( 2, 83 );
		levelToExperience.put( 3, 174 );
		levelToExperience.put( 4, 276 );
		levelToExperience.put( 5, 388 );
		levelToExperience.put( 6, 512 );
		levelToExperience.put( 7, 650 );
		levelToExperience.put( 8, 801 );
		levelToExperience.put( 9, 969 );
		levelToExperience.put( 10, 1154 );
		levelToExperience.put( 11, 1358 );
		levelToExperience.put( 12, 1584 );
		levelToExperience.put( 13, 1833 );
		levelToExperience.put( 14, 2107 );
		levelToExperience.put( 15, 2411 );
		levelToExperience.put( 16, 2746 );
		levelToExperience.put( 17, 3115 );
		levelToExperience.put( 18, 3523 );
		levelToExperience.put( 19, 3973 );
		levelToExperience.put( 20, 4470 );
		levelToExperience.put( 21, 5018 );
		levelToExperience.put( 22, 5624 );
		levelToExperience.put( 23, 6291 );
		levelToExperience.put( 24, 7028 );
		levelToExperience.put( 25, 7842 );
		levelToExperience.put( 26, 8740 );
		levelToExperience.put( 27, 9730 );
		levelToExperience.put( 28, 10824 );
		levelToExperience.put( 29, 12031 );
		levelToExperience.put( 30, 13363 );
		levelToExperience.put( 31, 14833 );
		levelToExperience.put( 32, 16456 );
		levelToExperience.put( 33, 18247 );
		levelToExperience.put( 34, 20224 );
		levelToExperience.put( 35, 22406 );
		levelToExperience.put( 36, 24815 );
		levelToExperience.put( 37, 27473 );
		levelToExperience.put( 38, 30408 );
		levelToExperience.put( 39, 33648 );
		levelToExperience.put( 40, 37224 );
		levelToExperience.put( 41, 41171 );
		levelToExperience.put( 42, 45529 );
		levelToExperience.put( 43, 50339 );
		levelToExperience.put( 44, 55649 );
		levelToExperience.put( 45, 61512 );
		levelToExperience.put( 46, 67983 );
		levelToExperience.put( 47, 75127 );
		levelToExperience.put( 48, 83014 );
		levelToExperience.put( 49, 91721 );
		levelToExperience.put( 50, 101333 );
		levelToExperience.put( 51, 111945 );
		levelToExperience.put( 52, 123660 );
		levelToExperience.put( 53, 136594 );
		levelToExperience.put( 54, 150872 );
		levelToExperience.put( 55, 166636 );
		levelToExperience.put( 56, 184040 );
		levelToExperience.put( 57, 203254 );
		levelToExperience.put( 58, 224466 );
		levelToExperience.put( 59, 247886 );
		levelToExperience.put( 60, 273742 );
		levelToExperience.put( 61, 302288 );
		levelToExperience.put( 62, 333804 );
		levelToExperience.put( 63, 368599 );
		levelToExperience.put( 64, 407015 );
		levelToExperience.put( 65, 449428 );
		levelToExperience.put( 66, 496254 );
		levelToExperience.put( 67, 547953 );
		levelToExperience.put( 68, 605032 );
		levelToExperience.put( 69, 668051 );
		levelToExperience.put( 70, 737627 );
		levelToExperience.put( 71, 814445 );
		levelToExperience.put( 72, 899257 );
		levelToExperience.put( 73, 992895 );
		levelToExperience.put( 74, 1096278 );
		levelToExperience.put( 75, 1210421 );
		levelToExperience.put( 76, 1336443 );
		levelToExperience.put( 77, 1475581 );
		levelToExperience.put( 78, 1629200 );
		levelToExperience.put( 79, 1798808 );
		levelToExperience.put( 80, 1986068 );
		levelToExperience.put( 81, 2192818 );
		levelToExperience.put( 82, 2421087 );
		levelToExperience.put( 83, 2673114 );
		levelToExperience.put( 84, 2951373 );
		levelToExperience.put( 85, 3258594 );
		levelToExperience.put( 86, 3597792 );
		levelToExperience.put( 87, 3972294 );
		levelToExperience.put( 88, 4385776 );
		levelToExperience.put( 89, 4842295 );
		levelToExperience.put( 90, 5346332 );
		levelToExperience.put( 91, 5902831 );
		levelToExperience.put( 92, 6517253 );
		levelToExperience.put( 93, 7195629 );
		levelToExperience.put( 94, 7944614 );
		levelToExperience.put( 95, 8771558 );
		levelToExperience.put( 96, 9684577 );
		levelToExperience.put( 97, 10692629 );
		levelToExperience.put( 98, 11805606 );
		levelToExperience.put( 99, 13034431 );
	}
	
	private static final Logger logger = Logger.getLogger( SkillImpl.class.getName() );
	
	private Type skillType;
	private int level;
	private int experience;
	private int rank;

	/**
	 * Initializes the skill from the api call.
	 */
	public SkillImpl( Type skillType, int level, int experience, int rank ) 
	{
		this.skillType = skillType;
		this.level = level;
		this.experience = experience;
		this.rank = rank;
	}
	
	public SkillImpl( Type skillType, int level )
	{
		this.skillType = skillType;
		this.level = level;
		
		this.experience = getExperienceForLevel( level );
		this.rank = -1; //who cares what the rank is if not calling api to initialize.
	}

	@Override
	public Type getType() 
	{
		return skillType;
	}

	@Override
	public int getLevel() 
	{
		return level;
	}

	@Override
	public int getExperience() 
	{
		return experience;
	}

	@Override
	public int getRank() 
	{
		return rank;
	}
	
	@Override
	public String toString() 
	{
		int expToNext = ( skillType != Type.Overall && skillType != Type.Combat && level != 99 ) ? ( levelToExperience.get( level+1 ) - experience ) : 0;
                return String.valueOf(level);
		//return "["+skillType.name()+"]" + " Level: " + level + ", experience: " + experience + ", rank: " + rank + ",experience to next level: " + expToNext;
	}

	@Override
	public int compareTo( Skill skill ) 
	{
		
		//compare based on the ordinal value of the enum. (order inputted into the enum)
		return this.skillType.ordinal() - skill.getType().ordinal();
		
	}
	
	public static int getExperienceForLevel( int level )
	{
		
		if( level < 1 || level > 100 )
		{
			return 0;
		}
		else
		{
			return levelToExperience.get( level );
		}
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
		
	}

	@Override
	public void setExperience(int experience) {
		this.experience = experience;
		
	}

	@Override
	public void setRank(int rank) {
		this.rank = rank;
		
	}
}
