package com.tnesoftware.rsquestfilter.impl;

import com.tnesoftware.rsquestfilter.Item;

/**
 * Each item should have a name and a quantity associated with it for quests.
 *
 * @author Trentin Thomas
 *
 */
public class ItemImpl implements Item {

   private String name;
   private int quantity;

   public ItemImpl(String name, int quantity) {
      this.name = name;
      this.quantity = quantity;
   }

   @Override
   public int getQuantity() {
      return quantity;
   }

   @Override
   public String getName() {
      return name;
   }

   @Override
   public String toString() {
      return "[" + name + "]" + " Amount: " + quantity;
   }

}
