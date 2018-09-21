package com.tnesoftware.rsquestfilter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.tnesoftware.rsquestfilter.Skill.Type;

/**
 * Player holds all the Quests that they have completed, can do, all the players skills, and a 
 * way to update all the skills so that we can keep track of all the quests that they can do.
 * @author Trentin Thomas
 *
 */
public interface Player extends Serializable
{
	/**
	 * Gets all quests.
	 */
	public List<Quest> getAllQuests();
	
	/**
	 * Gets only completed quests by this player. They had to mark which ones they have completed.
	 * @return
	 */
	public List<Quest> getCompletedQuests();
	
	/**
	 * Gets the current skills define by the hiscore of this player.
	 * @return
	 */
	public List<Skill> getSkills();
	
	/**
	 * Gets the Quest Manager, which handles quest updates and filtering.
	 * @return
	 */
	public Quest.Manager getQuestManager();
	
	/**
	 * Name of the Quest
	 * @return
	 */
	public String getName();
	
	/**
	 * This is for setting skill values using the hiscores api
	 * @param skillNum
	 * @param line
	 */
	public void setSkill( int skillNum, String line );
	
	/**
	 * overloaded method of above.
	 * @param skillNum
	 * @param level
	 * @param experience
	 * @param rank
	 */
	public void setSkill( int skillNum, int level, int experience, int rank );
	
	/**
	 * overloaded method of above
	 * @param skillType
	 * @param level
	 * @param experience
	 * @param rank
	 */
	public void setSkill( Type skillType, int level, int experience, int rank );
	
	/**
	 * Updates the skill given the skillType, then passing in the new values.
	 * @param skillType
	 * @param level
	 * @param experience
	 * @param rank
	 */
	public void updateSkill( Type skillType, int level, int experience, int rank );
	
	/**
	 * Calls the hiscores api again to update player's skills.
	 */
	public void updateAllSkills();
	
	/**
	 * Gets the Skill object by using the Type.
	 * @param skillType
	 * @return
	 */
	public Skill getSkill( Type skillType );
	
	/**
	 * Determines if the player can complete the quest.
	 * @param quest
	 * @return
	 */
	public boolean canComplete( Quest quest );

	/**
	 * Calculates the player's combat level.
	 * @return cblevel
	 */
	public int calculateCombat();
	
	/**
	 * Returns the number of quest points the player has.
	 * @return
	 */
	public int getQuestPoints();

	public void calculateQuestPoints();

}
