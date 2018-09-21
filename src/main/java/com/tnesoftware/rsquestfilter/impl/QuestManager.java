package com.tnesoftware.rsquestfilter.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.tnesoftware.rsquestfilter.Player;
import com.tnesoftware.rsquestfilter.Quest;
import com.tnesoftware.rsquestfilter.tools.QuestConstants;

public class QuestManager implements Quest.Manager, Serializable {
	
	private List<Quest> allQuests;
	private Player player;

	public QuestManager( Player player ) {
		allQuests = new ArrayList<Quest>();
		initializeQuests();
		this.player = player;
		
		
	}

	@Override
	public List<Quest> getAllQuests() {
		allQuests.sort( (q1, q2) -> q1.getName().compareTo( q2.getName() ) );
		return allQuests;
	}

	/**
	 * Gets all quests completed by the player.
	 * @return
	 */
	@Override
	public List<Quest> getQuestsCompleted() {
		return allQuests.stream()
				.filter( Quest::isComplete )
				.sorted( (q1, q2) -> q1.getName().compareTo(q2.getName()))
				.collect( Collectors.toCollection( ArrayList::new ) );
	}

	/**
	 * Get all quests not completed by the player.
	 * @return
	 */
	@Override
	public List<Quest> getQuestsNotCompleted() 
	{
		return allQuests.stream()
				.filter( q -> !q.isComplete() )
				.sorted( (q1, q2) -> q1.getName().compareTo(q2.getName()))
				.collect( Collectors.toCollection( ArrayList::new ) );
	}
	
	public List<Quest> sortBasedOnDifficulty( boolean ascending )
	{
		return allQuests.stream().sorted( (q1, q2) -> ascending 
			? q1.getDifficulty().compareTo( q2.getDifficulty() ) == 0 ? q1.getName().compareTo( q2.getName() ) : q1.getDifficulty().compareTo( q2.getDifficulty() ) 
			: q2.getDifficulty().compareTo( q1.getDifficulty() ) == 0 ? q1.getName().compareTo( q2.getName() ) : q2.getDifficulty().compareTo( q1.getDifficulty() ) )
			.collect( Collectors.toCollection( ArrayList::new ) );
	}
	
	public List<Quest> sortBasedOnLength( boolean ascending )
	{
		return allQuests.stream().sorted( (q1, q2) -> ascending 
				? q1.getLength().compareTo( q2.getLength() ) == 0 ? q1.getName().compareTo( q2.getName() ) : q1.getLength().compareTo( q2.getLength() )
				: q2.getLength().compareTo( q1.getLength() ) == 0 ? q1.getName().compareTo( q2.getName() ) : q2.getLength().compareTo( q1.getLength() ) )
				.collect( Collectors.toCollection( ArrayList::new ) );
	}
	
	public List<Quest> sortBasedOnQuestPoints( boolean ascending )
	{
		return allQuests.stream().sorted( (q1, q2) -> ascending 
				? Integer.compare( q1.getQuestPoints(), q2.getQuestPoints() ) == 0 ? q1.getName().compareTo( q2.getName() ) : Integer.compare( q1.getQuestPoints(), q2.getQuestPoints() )
				: Integer.compare( q2.getQuestPoints(), q1.getQuestPoints() ) == 0 ? q1.getName().compareTo( q2.getName() ) : Integer.compare( q2.getQuestPoints(), q1.getQuestPoints() ) )
				.collect( Collectors.toCollection( ArrayList::new ) );
	}

	/**
	 * Finds a quest given a quest name. If not found, then returns null.
	 * @param name
	 * @return
	 */
	@Override
	public Quest findQuest( String name ) {
		try
		{
			return allQuests.stream().filter( q -> q.getName().equalsIgnoreCase( name ) ).findFirst().get();
		}
		catch( NoSuchElementException nsee )
		{
			//try to find if a quest contains  the string?
			try
			{
				return allQuests.stream().filter( q -> q.getName().contains( name ) ).findFirst().get();
			}
			catch( NoSuchElementException nsee2 )
			{
				nsee.printStackTrace();
				nsee2.printStackTrace();
				return null;
			}
		}
	}

	@Override
	public List<Quest> getQuestsCanBeCompleted() {
		return allQuests.stream()
				.filter( q -> player.canComplete( q ) )
				.sorted( (q1, q2) -> q1.getName().compareTo(q2.getName()))
				.collect( Collectors.toCollection( ArrayList::new ) );
	}
	
	private void initializeQuests()
	{
		QuestConstants.allQuestSet.stream()
			.forEach( q -> allQuests.add( new QuestImpl( q ) ) );
		allQuests.sort((q1, q2) -> q1.getName().compareTo(q2.getName()));
	}

	@Override
	public void setQuestComplete( Quest quest, boolean isComplete ) {
		quest.setComplete( isComplete );
		player.calculateQuestPoints();
		
	}

	@Override
	public List<Quest> getQuestsCanBeCompletedAndHideComplete() {
		return getQuestsCanBeCompleted().stream().filter( q -> !q.isComplete() )
		.sorted( (q1, q2) -> q1.getName()
				.compareTo(q2.getName()))
		.collect(Collectors.toCollection( ArrayList::new ) );
	}
	

}
