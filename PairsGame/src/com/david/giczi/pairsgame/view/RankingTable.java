package com.david.giczi.pairsgame.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class RankingTable {

	private JFrame frame;
	private String[] column= {"PLACE", "NAME", "STEPS", "GAME PERIOD [SEC]", "DATE"};
	
	public RankingTable(String[][] data) {
		
		frame=new JFrame("Ranking List");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JTable table=new JTable(data, column);
		table.setBackground(Color.WHITE);      
		table.setForeground(Color.RED); 
		
		JScrollPane pane=new JScrollPane(table);
		
		frame.add(pane);
		frame.setVisible(true);
		
	}
	
	
}
