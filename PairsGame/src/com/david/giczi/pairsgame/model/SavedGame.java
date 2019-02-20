package com.david.giczi.pairsgame.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.swing.JButton;

public class SavedGame implements Serializable {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton[] numberButtons;
	private List<Integer> guessedNumbers;
	private int previousClickedButtonIndex;
	private boolean isSecondClick;
	private int previousClickedButtonNumber;
	private int pairCounter;
	private int clickCounter;
	private int secCounter;
	
	
	public SavedGame() {
		
	}
	
	public SavedGame(JButton[] tableButtons, List<Integer> guessedNumbers,int previousClickedButtonIndex,
			boolean isSecondClick, int previousClickedButtonNumber, int pairCounter, int clickCounter, int secCounter) {
		
		
		this.numberButtons=tableButtons;
		this.guessedNumbers=guessedNumbers;
		this.previousClickedButtonIndex = previousClickedButtonIndex;
		this.isSecondClick = isSecondClick;
		this.previousClickedButtonNumber = previousClickedButtonNumber;
		this.pairCounter = pairCounter;
		this.clickCounter = clickCounter;
		this.secCounter = secCounter;
	}


	
	
	public JButton[] getNumberButtons() {
		return numberButtons;
	}




	public int getPreviousClickedButtonIndex() {
		return previousClickedButtonIndex;
	}




	public boolean isSecondClick() {
		return isSecondClick;
	}




	public int getPreviousClickedButtonNumber() {
		return previousClickedButtonNumber;
	}




	public int getPairCounter() {
		return pairCounter;
	}




	public int getClickCounter() {
		return clickCounter;
	}




	public int getSecCounter() {
		return secCounter;
	}



	public List<Integer> getGuessedNumbers() {
		return guessedNumbers;
	}


	public void saveGameToFile(SavedGame game) {
		
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File("./save/game.txt")));
			
			out.writeObject(game);
			
			out.close();
			
		} catch (IOException e) {
			
			
			e.printStackTrace();
		}
		
	}
	
	public SavedGame loadGameFromFile() throws IOException {
		
		SavedGame game=null;
		
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(new File("./save/game.txt")));
			
			game=(SavedGame) in.readObject();
			
			in.close();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return game;
	}

	
	
	
}
