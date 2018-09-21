package com.tnesoftware.rsquestfilter;

import java.util.HashSet;
import java.util.Set;

import com.tnesoftware.rsquestfilter.Skill.Type;
import com.tnesoftware.rsquestfilter.impl.PlayerImpl;
import com.tnesoftware.rsquestfilter.impl.SkillImpl;

public class Skill_Test {

	public static void main(String[] args) throws Throwable
	{
		Set<Skill> skills = new HashSet<Skill>();
		
		skills.add( new SkillImpl( Type.Agility, 32, 1000, 1) );
		skills.add( new SkillImpl( Type.Attack, 50, 100000, 3) );
		
		skills.stream().forEach( System.out::println );
		
		Player player = new PlayerImpl("Aluminoti");
		System.out.println(player.calculateCombat());
	}

}
