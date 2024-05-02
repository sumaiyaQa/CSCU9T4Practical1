// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    // Constructor and other methods omitted for brevity

    // Method to remove an entry based on name and date
    public boolean removeEntry(String name, int day, int month, int year) {
        Iterator<Entry> iterator = tr.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (entry.getName().equals(name) && entry.getDay() == day && entry.getMonth() == month && entry.getYear() == year) {
                iterator.remove(); // Remove the entry from the ArrayList
                return true; // Return true indicating successful removal
            }
        }
        return false; // Return false if entry not found
    }
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public String addEntry(Entry e) {
       {
           String message = "Record added\n";
           String name = e.getName();
           int day = e.getDay();
           int month = e.getMonth();
           int year = e.getYear();
           boolean record = false;

           ListIterator<Entry> iter = tr.listIterator();
           while (iter.hasNext()) {
               Entry current = iter.next();
               if (current.getName().equalsIgnoreCase(name) && current.getDay() == day && current.getMonth() == month && current.getYear() == year) {
                   message = "Record already exists.\n";
                   record = true;
                   break;
               }

           }

           if (!record) {
               tr.add(e);
           }

           return message;

       } // addClass
   }
   // look up the entry of a given day and month
   public String lookupEntry (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "No entries found";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result = current.getEntry();
            }
       return result;
   } // lookupEntry
   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }

    public String lookupEntries(int day, int month, int year) {
        ListIterator<Entry> iter = tr.listIterator();
        String result = "";
        while (iter.hasNext()) {
            Entry current = iter.next();
            if (current.getDay()==day && current.getMonth()==month && current.getYear()==year)
                result += current.getEntry();
        }
        if(result.isBlank())
        {
            result="Sorry couldn't find anything for this date";
        }
        return result;
    }
   
} // TrainingRecord
