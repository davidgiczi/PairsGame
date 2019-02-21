package com.david.giczi.pairsgame.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;

import com.david.giczi.pairsgame.model.DBaseConnect;
import com.david.giczi.pairsgame.model.PairsGameLogic;
import com.david.giczi.pairsgame.model.SavedGame;
import com.david.giczi.pairsgame.view.PairsGameTable;
import com.david.giczi.pairsgame.view.RankingTable;

public class GuessNumberController implements ActionListener {
	
	
	private static PairsGameLogic logic;
	private DBaseConnect connect;
	private PairsGameTable table;
	
	
	
	public GuessNumberController(PairsGameTable table) {
		
		this.table=table;
		logic=new PairsGameLogic();
		connect=DBaseConnect.getInstace();
		
	}
	


	@Override
	public void actionPerformed(ActionEvent event)  {
		
		JButton clickedButton=null;
		String buttonName=null;
		
		if(event.getSource() instanceof JButton) {
			
			
		clickedButton=(JButton) event.getSource();
		buttonName=clickedButton.getText();
		
		
		switch (buttonName) {
		
		case "Start":
			
		startGame();
		
			break;

		case "Stop":
			
			stopGame();
			
			break;
		
		case "New Game":
			
			createNewGame();
			
			break;
		
		case "Save Table":
				
			saveGame();
			
			break;
			
		case "Load Table":
			
			loadGame();
			
			break;
		
		case "Clear Ranking":
			
			clearRanking();
				
			break;
			
		case "Ranking":
			
			
			getRankingTable();
			
				
			break;
		
		case "Exit":
			
			System.exit(0);
			
			break;
		default:
			

			boolean isPair=logic.wasClickedTheButtonInPrevoiusStep(clickedButton.getText());
			table.clickingTableButton(clickedButton, isPair);
			
			if(logic.getPairCounter()==10) {
			
			closeTheGame();
		}
			
			
		}
	
			}
		
		if(table.getTimer().isRunning()) {
			countSec();
		}
		
		
	}

	
	
	private void startGame() {
		
		if(logic.getGuessedNumbers()==null) {
			logic.generatingNumber();
			
		}
		
		
		//showTableNumber(); //if you need for testing
		
		table.clickStartButton(logic.getGuessedNumbers());
		
		table.getStart().setEnabled(false);
		table.getStop().setEnabled(true);
		table.getSave().setEnabled(false);
		table.getLoad().setEnabled(false);
		table.getNewGame().setEnabled(false);
		table.getTimer().start();
		
	}
	
	
	private void showTableNumber() {
		
		for (int i=0; i<4; i++) {
			
		for (int j = 0; j < 5; j++) {
			
			System.out.print(logic.getGuessedNumbers().get(i*5+j)+" ");
		}
		System.out.println();
	}
		
	System.out.println();
		
	}
	
	
	private void stopGame() {
		
		
		table.clickStopButton();
		
		table.getStart().setEnabled(true);
		table.getStop().setEnabled(false);
		table.getSave().setEnabled(true);
		table.getLoad().setEnabled(true);
		table.getNewGame().setEnabled(true);
		table.getTimer().stop();
	}

	private void countSec() {
		
		int secCounter=logic.getSecCounter();
		
		int min=secCounter/60;
		int sec=secCounter%60;
		String timeText=sec<10  ? min+":0"+sec : +min+":"+sec;
	
		table.setTitle(timeText);
		
		secCounter++;
		
		logic.setSecCounter(secCounter);
	}
	
	private void createNewGame() {
		
		table.destroyTable();
		logic.init();
		table.setSecondClick(false);
		table.createTable();
		
		table.getStart().setEnabled(true);
		
	}
	
	
	private void getRankingTable() {
	
	try {
		
		new RankingTable(logic.convertingRankingData(connect.getAllResults()));
		
	} catch (SQLException e) {
		
		connect.createTable();
		table.informing("\'results\' table has created. If you want to look at it, please, click \'Ranking\' button again.");
	}
		
		table.getClearTable().setEnabled(true);
	
	}
	
	private void closeTheGame() {
		
		
		table.getTimer().stop();
		
		table.setPlayerName("The game has ended in "+logic.getClickCounter()+" steps.\nPlease, add your name:");
		
		if(table.getPlayerName()!=null) {
	
			try {
				connect.insert(table.getPlayerName(), logic.getClickCounter(), logic.getSecCounter()-1, logic.createTimeStamp());
				
			} catch (SQLException e) {
				
				connect.createTable();
			}
}
			
		table.getStop().setEnabled(false);
		table.getNewGame().setEnabled(true);
		table.getSave().setEnabled(true);
		table.getLoad().setEnabled(true);
		
		
	}
	
	private void clearRanking() {
		
		if(table.toBeClearedTheRankingList()) {
			
		connect.truncateTable();
		table.informing("Data of Ranking list has cleared.");
			
		}
		
	}
	
	
	private void saveGame() {
		
		SavedGame gameSaver=new SavedGame(table.getNumbersButtons(), logic.getGuessedNumbers(), table.getPreviousClickedButtonIndex(),
				table.isSecondClick(), logic.getPreviousClickedButtonNumber(), logic.getPairCounter(), logic.getClickCounter(), logic.getSecCounter()-1);
		
		
		gameSaver.saveGameToFile(gameSaver);
		
		table.informing("The game has been saved succesfully.");
		
	}
	
	private void loadGame() {
		
		
		SavedGame gameSaver=new SavedGame();
		
		try {
			
			SavedGame game=gameSaver.loadGameFromFile();
			
			table.destroyTable();
			table.setNumbersButtons(game.getNumberButtons());
			table.setPreviousClickedButtonIndex(game.getPreviousClickedButtonIndex());
			table.setSecondClick(game.isSecondClick());
			table.createTable(table.getNumbersButtons());

			logic.setGuessedNumbers(game.getGuessedNumbers());
			logic.setPreviousClickedButtonNumber(game.getPreviousClickedButtonNumber());
			logic.setPairCounter(game.getPairCounter());
			logic.setClickCounter(game.getClickCounter());
			logic.setSecCounter(game.getSecCounter());
		
			countSec();
			
			table.getStart().setEnabled(true);
			
			
			
		} catch (IOException e) {
			
			table.informing("Game has not saved before, please, save one.");
		}
		
		
		
		
		
	}

}
	
	
