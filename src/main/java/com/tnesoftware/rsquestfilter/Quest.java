package com.tnesoftware.rsquestfilter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.tnesoftware.rsquestfilter.Skill.Type;

/**
 * Quest interface to be implemented by QuestImpl. This defines what it means to be a Quest,
 * such as the requirements associated, the items associated, and skill levels associated to
 * complete a quest.
 * @author Trentin Thomas
 *
 */
public interface Quest extends Comparable<Quest>, Serializable {
	
	public interface Manager {
		
		/**
		 * Gets all quests
		 * @return
		 */
		public List<Quest> getAllQuests();
		
		public List<Quest> getQuestsCompleted();
		
		public List<Quest> getQuestsNotCompleted();
		
		public List<Quest> sortBasedOnDifficulty( boolean asc );
		public List<Quest> sortBasedOnLength( boolean asc );
		
		/**
		 * returns a sorted quest set based
		 * @param asc
		 * @return
		 */
		public List<Quest> sortBasedOnQuestPoints( boolean asc );
		
		/**
		 * Finds the quest object based on the String passed in. This will try and find
		 * it exactly as passed in first, ignoring case. If it cannot find an object, then it
		 * will try to search for a quest name that contains the string passed in.
		 * @param name
		 * @return
		 */
		public Quest findQuest( String name );

		/**
		 * Gets all quests that can be completed by the player that is not already marked
		 * as completed.
		 * @return
		 */
		public List<Quest> getQuestsCanBeCompleted();
		
		public List<Quest> getQuestsCanBeCompletedAndHideComplete();

		public void setQuestComplete(Quest quest, boolean b);
		
	}
	
	public enum Length { Short, Medium, Long };
	public enum Difficulty { Novice, Intermediate, Experienced, Master, Grandmaster };
	
	public List<Quest> getQuestRequirements();
	
	public List<Item> getItemRequirements();
	
	public List<Skill> getSkillRequirements();
	
	public Length getLength(); 
	
	public boolean isComplete();
	
	public String getURLToRuneHQQuestGuide();
	
	public void setQuestRequirements( Quest ... quests );
	
	public void setItemRequirements( Item ... items );
	
	public void setSkillRequirements( Skill ... skills );
	
	public void setSkillRequirements( Type type, int level );
	
	public String getName();
	
	public int getQuestPoints();
	
	public Difficulty getDifficulty();
	
	public boolean isEqual( Quest quest );
	
	void setComplete( boolean complete );

}
