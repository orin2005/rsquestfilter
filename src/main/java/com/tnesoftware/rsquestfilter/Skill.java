package com.tnesoftware.rsquestfilter;

import java.io.Serializable;

public interface Skill extends Comparable<Skill>, Serializable {
	
	public enum Type { Overall, Attack, Defence, Strength, Hitpoints, Ranged,
		Prayer, Magic, Cooking, Woodcutting,  Fletching, Fishing,
		Firemaking, Crafting, Smithing, Mining, Herblore, Agility,
		Thieving, Slayer, Farming, Runecraft, Hunter, Construction, Quest, Combat };
		
	/**
	 * Returns what type of skill it is.
	 * @return
	 */
	public Type getType(); 
	
	/**
	 * Gets the current level of the skill
	 * @return
	 */
	public int getLevel();
	
	/**
	 * Gets the experience of the skill
	 * @return
	 */
	public int getExperience();
	
	/**
	 * Gets the rank of the skill
	 * @return
	 */
	public int getRank();
	
	/**
	 * Determines if the skill's level is greater than the level of the passed in skill.
	 * @param skill
	 * @return
	 */
	default boolean greaterThanOrEqualTo( Skill skill )
	{
		if( skill.getType().equals( this.getType() ) )
			return this.getLevel() >= skill.getLevel();
		else
			throw new IllegalArgumentException( "You gave the wrong skill type to compare. This skill is "
						+ getType().name() + ". Passed in skill of type " + skill.getType().name() );
	}
	
	public void setLevel( int level );
	
	public void setExperience( int experience );
	
	public void setRank( int rank );
		

}
