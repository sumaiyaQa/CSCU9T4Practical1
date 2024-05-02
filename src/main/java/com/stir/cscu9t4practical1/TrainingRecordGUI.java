// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate=new JButton("Find All By Date:");
    private JButton removeEntry = new JButton("Remove");

    private JComboBox<String> entryTypeComboBox;

    private JPanel cycleEntryPanel = new JPanel();
    private JPanel sprintEntryPanel = new JPanel();
    private JPanel swimEntryPanel = new JPanel();




    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        add(findAllByDate);
        findAllByDate.addActionListener(this);

        add(removeEntry);
        removeEntry.addActionListener(this); // Add action listener for the remove button



        String[] entryTypes = {"Swim", "Cycle", "Sprint"};
        entryTypeComboBox = new JComboBox<>(entryTypes);
        add(entryTypeComboBox);
        entryTypeComboBox.addActionListener(this);



        add(cycleEntryPanel);
        cycleEntryPanel.setVisible(false);


        add(sprintEntryPanel);
        sprintEntryPanel.setVisible(false);


        add(swimEntryPanel);
        swimEntryPanel.setVisible(false);

        setSize(720, 200);
        setVisible(true);
        blankDisplay();


        
    } // constructor


    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        String selectedEntryType = (String) entryTypeComboBox.getSelectedItem(); // Declare selectedEntryType here
        if (event.getSource() == addR) {
            String message2 = addEntry(selectedEntryType);
            outputArea.setText(message2);
            blankDisplay();
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == removeEntry) { // Check if the remove button is clicked
            message = removeEntry();
        }
        if (event.getSource() == findAllByDate) {
            String dayText = day.getText();
            String monthText = month.getText();
            String yearText = year.getText();

            try {
                int d = Integer.parseInt(dayText);
                int m = Integer.parseInt(monthText);
                int y = Integer.parseInt(yearText);

                message = myAthletes.lookupEntries(d, m, y);
            } catch (NumberFormatException e) {
                message = "Please enter valid day, month, and year values.";
            }
        }

        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
        String message = "Record added\n";
        System.out.println("Adding " + what + " entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());

        // Handle specific entry types
        if (what.equals("Cycle")) {
            String terrain = JOptionPane.showInputDialog("Enter Terrain:");
            String tempo = JOptionPane.showInputDialog("Enter Tempo:");
            CycleEntry e = new CycleEntry(n, d, m, y, h, mm, s, km, terrain, tempo);
            myAthletes.addEntry(e);
        } else if (what.equals("Swim")) {
            String location = JOptionPane.showInputDialog("Enter Location:");
            SwimEntry e = new SwimEntry(n, d, m, y, h, mm, s, km, location);
            myAthletes.addEntry(e);
        } else if (what.equals("Sprint")) {
            int reps = Integer.parseInt(JOptionPane.showInputDialog("Enter Repetitions:"));
            int rec = Integer.parseInt(JOptionPane.showInputDialog("Enter Recovery (minutes):"));
            SprintEntry e = new SprintEntry(n, d, m, y, h, mm, s, km, reps, rec);
            myAthletes.addEntry(e);
        }


        return message;
    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    public String removeEntry() {
        String message = "";
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());

        // Remove the entry from the training record
        boolean removed = myAthletes.removeEntry(n, d, m, y);
        if (removed) {
            message = "Entry removed successfully.";
        } else {
            message = "Entry not found.";
        }
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }


}

 // TrainingRecordGUI

