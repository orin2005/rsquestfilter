package com.tnesoftware.rsquestfilter.ui;

import com.tnesoftware.rsquestfilter.Quest;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Erik
 */
public class Table {
   //d = destination
   //s = source

   private final SimpleStringProperty dQuest;
   private final SimpleStringProperty dLength;
   private final SimpleIntegerProperty dQp;
   private final SimpleBooleanProperty dIsComplete;
   private Quest quest;

   public Table(Quest quest) {
      this.quest = quest;
      this.dQuest = new SimpleStringProperty(quest.getName());
      this.dLength = new SimpleStringProperty(quest.getLength().name());
      this.dQp = new SimpleIntegerProperty(quest.getQuestPoints());
      this.dIsComplete = new SimpleBooleanProperty(quest.isComplete());
   }

   public String getName() {
      if (!quest.getName().equals(dQuest.get())) {
         dQuest.set(quest.getName());
      }
      return dQuest.get();
   }

   public String getLength() {
      if (!quest.getLength().equals(dLength.get())) {
         dLength.set(quest.getLength().name());
      }
      return dLength.get();

   }

   public Integer getQp() {
      if (dQp.get() != quest.getQuestPoints()) {
         dQp.set(quest.getQuestPoints());
      }
      return dQp.get();
   }

   public Boolean getIsComplete() {
      if (dIsComplete.get() != quest.isComplete()) {
         dIsComplete.set(quest.isComplete());
      }
      return dIsComplete.getValue();
   }

   public void setIsComplete() {

      dIsComplete.setValue(!dIsComplete.getValue());
   }
}
