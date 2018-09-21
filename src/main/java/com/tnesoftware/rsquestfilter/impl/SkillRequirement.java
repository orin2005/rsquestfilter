package com.tnesoftware.rsquestfilter.impl;

import com.tnesoftware.rsquestfilter.Skill;

public class SkillRequirement implements Skill {

   private Type skillType;
   private int level;

   public SkillRequirement(Type skillType, int level) {
      this.skillType = skillType;
      this.level = level;
   }

   @Override
   public int compareTo(Skill s) {
      SkillRequirement sr = (SkillRequirement) s;

      return skillType.compareTo(sr.skillType);
   }

   @Override
   public Type getType() {
      return skillType;
   }

   @Override
   public int getLevel() {
      return level;
   }

   @Override
   public int getExperience() {
      return SkillImpl.getExperienceForLevel(level);
   }

   @Override
   public int getRank() {
      return -1;
   }

   @Override
   public boolean greaterThanOrEqualTo(Skill skill) {
      return level >= skill.getLevel();
   }

   @Override
   public void setLevel(int level) {
      this.level = level;

   }

   @Override
   public void setExperience(int experience) {
      return;

   }

   @Override
   public void setRank(int rank) {
      return;

   }

   @Override
   public String toString() {
      return "[" + skillType.name() + "]" + " Level: " + level;
   }

}
