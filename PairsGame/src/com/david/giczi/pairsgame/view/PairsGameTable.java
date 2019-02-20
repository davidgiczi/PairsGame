package com.david.giczi.pairsgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.david.giczi.pairsgame.controller.GuessNumberController;



public class PairsGameTable {
	
	private JFrame frame;
	private JButton start;
	private JButton stop;
	private JButton newGame;
	private JButton save;
	private JButton load;
	private JButton ranking;
	private JButton clearTable;
	private JButton exit;
	private JPanel playingPanel;
	private JPanel controlPanel;
	private JButton[] numbersButtons;
	private int previousClickedButtonIndex;
	private boolean isSecondClick=false;
	private Timer timer=new Timer(1000, new GuessNumberController(this));
	private String playerName;
	
	
	
	public PairsGameTable() {
		
		
		numbersButtons=new JButton[20];
		
		
		for (int i=0; i<numbersButtons.length; i++) {
			numbersButtons[i]=new JButton();
			numbersButtons[i].addActionListener(new GuessNumberController(this));
		}
		
		start=new JButton("Start");
		stop=new JButton("Stop");
		newGame=new JButton("New Game");
		save=new JButton("Save Table");
		load=new JButton("Load Table");
		ranking=new JButton("Ranking");
		clearTable=new JButton("Clear Ranking");
		exit=new JButton("Exit");
		
		addListenerToTheControlPanelButtons();
		
		
	}
	

	public void  createTable() {
		
		frame=new JFrame("GuessNumber Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(980, 700);
		frame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-frame.getSize().getWidth())/2,
						  (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-frame.getSize().getHeight())/2);
		
		initPlayingPanel();
		initControlPanel();
		
		
		frame.add(playingPanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	public void  createTable(JButton[] tableButtons) {
		
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(980, 700);
		frame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-frame.getSize().getWidth())/2,
						  (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-frame.getSize().getHeight())/2);
		
		initPlayingPanel(tableButtons);
		initControlPanel();
		
		frame.add(playingPanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
		
	
	public JFrame getFrame() {
		return frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public JButton getStart() {
		return start;
	}




	public void setStart(JButton start) {
		this.start = start;
	}




	public JButton getStop() {
		return stop;
	}


	public void setStop(JButton stop) {
		this.stop = stop;
	}



	public JButton getNewGame() {
		return newGame;
	}



	public void setNewGame(JButton newGame) {
		this.newGame = newGame;
	}



	public JButton getSave() {
		return save;
	}



	public void setSave(JButton save) {
		this.save = save;
	}


	
	public JButton getLoad() {
		return load;
	}



	public void setLoad(JButton load) {
		this.load = load;
	}


	
	public JButton getRanking() {
		return ranking;
	}


	public void setRanking(JButton ranking) {
		this.ranking = ranking;
	}



	public JButton getExit() {
		return exit;
	}



	public void setExit(JButton exit) {
		this.exit = exit;
	}



	public JPanel getPlayingPanel() {
		return playingPanel;
	}



	public void setPlayingPanel(JPanel playingPanel) {
		this.playingPanel = playingPanel;
	}



	public JPanel getControlPanel() {
		return controlPanel;
	}



	public void setControlPanel(JPanel controlPanel) {
		this.controlPanel = controlPanel;
	}



	public JButton[] getNumbersButtons() {
		return numbersButtons;
	}


	public void setNumbersButtons(JButton[] numbersButtons) {
		this.numbersButtons = numbersButtons;
	}


	
	public Timer getTimer() {
		return timer;
	}


	
	
	public String getPlayerName() {
		return playerName;
	}
	

	public int getPreviousClickedButtonIndex() {
		return previousClickedButtonIndex;
	}

	public void setPreviousClickedButtonIndex(int previousClickedButtonIndex) {
		this.previousClickedButtonIndex = previousClickedButtonIndex;
	}

	public boolean isSecondClick() {
		return isSecondClick;
	}

	public void setSecondClick(boolean isSecondClick) {
		this.isSecondClick = isSecondClick;
	}

	
	public JButton getClearTable() {
		return clearTable;
	}


	public boolean toBeClearedTheRankingList() {
		
		if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(frame, "Are you sure clearing the Ranking list? Data cannot be restored!", "Warning", JOptionPane.YES_OPTION)) {
			
			return true;
		}
			
				
		return false;
	}
	
	public void clickingTableButton(JButton clickedButton, boolean isPair) {
		
		
		
		if(!isSecondClick) {
			
			clickedButton.setForeground(Color.RED);
			clickedButton.setEnabled(false);
			
		}
		
		
		if(isSecondClick && isPair) {
			
			clickedButton.setForeground(Color.RED);
			clickedButton.setEnabled(false);
			
		}
		
		if(isSecondClick && !isPair){
				
			numbersButtons[previousClickedButtonIndex].setForeground(Color.WHITE);
			numbersButtons[previousClickedButtonIndex].setEnabled(true);
			
		}
			
		
		for (int i=0; i<numbersButtons.length; i++) {
			if(clickedButton==numbersButtons[i]) {
				previousClickedButtonIndex=i;
			}
		}
		
		
		isSecondClick=!isSecondClick;
		
		
	}
	
	
	public void setTitle(String text) {
		
		frame.setTitle("GuessNumber Game - "+text);
		
	}
	
	public void destroyTable() {
		
		frame.setVisible(false);
		frame=null;
		
	}
	
	
	public void clickStartButton(List<Integer> guessedNumbers) {
		
		for (int i=0; i<getNumbersButtons().length; i++) {
			
			if(!"".equals(getNumbersButtons()[i].getText())) {
				getNumbersButtons()[i].setForeground(Color.RED);
			}
			
			
			getNumbersButtons()[i].setText(String.valueOf(guessedNumbers.get(i)));
			
		
			if(getNumbersButtons()[i].getForeground()!=Color.RED) {
				
				getNumbersButtons()[i].setEnabled(true);
			}
			else {
				getNumbersButtons()[i].setEnabled(false);
			}
			
		}
		 
	}
	
	public void clickStopButton() {
		
			for (int i=0; i<getNumbersButtons().length; i++) {
			
			if(getNumbersButtons()[i].isEnabled()) {
				getNumbersButtons()[i].setText("");
			}
			
			getNumbersButtons()[i].setEnabled(false);
		}
		
	}
	
	public void informing(String text) {
		
		JOptionPane.showMessageDialog(frame, text, "Info", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void setPlayerName(String text) {
		
		playerName=JOptionPane.showInputDialog(text);
		
	}
	
	
	private void initPlayingPanel() {
		
		playingPanel=new JPanel();
		Font font=new Font("Book Antiqua", Font.BOLD, 60);
		
		playingPanel.setLayout(new GridLayout(4, 5));
		
		for (int i=0; i<numbersButtons.length; i++) {
			numbersButtons[i].setBackground(Color.WHITE);
			numbersButtons[i].setForeground(Color.WHITE);
			numbersButtons[i].setFont(font);
			numbersButtons[i].setText("");
			numbersButtons[i].setEnabled(false);
			playingPanel.add(numbersButtons[i]);
		}
		
		
	}
	

	
	private void initControlPanel() {
		
		controlPanel=new JPanel();
		Font font=new Font("Book Antiqua", Font.BOLD, 20);
		
		controlPanel.setBackground(new Color(70,130,180));
		
		
		start.setFont(font);
		start.setForeground(Color.RED);
		controlPanel.add(start);
		
		stop.setFont(font);
		stop.setForeground(Color.RED);
		stop.setEnabled(false);
		controlPanel.add(stop);
		
		newGame.setFont(font);
		newGame.setForeground(Color.RED);
		newGame.setEnabled(false);
		controlPanel.add(newGame);
		
		save.setFont(font);
		save.setForeground(Color.RED);
		save.setEnabled(false);
		controlPanel.add(save);
		
		load.setFont(font);
		load.setForeground(Color.RED);
		controlPanel.add(load);
		
		ranking.setFont(font);
		ranking.setForeground(Color.RED);
		controlPanel.add(ranking);
		
		clearTable.setFont(font);
		clearTable.setForeground(Color.RED);
		clearTable.setEnabled(false);
		controlPanel.add(clearTable);
		
		exit.setFont(font);
		exit.setForeground(Color.RED);
		controlPanel.add(exit);
		
	}
	
	
	private void initPlayingPanel(JButton[] tableButtons) {
		
		playingPanel=new JPanel();
		
		playingPanel.setLayout(new GridLayout(4, 5));
		
		for(int i=0; i<tableButtons.length; i++) {
			
			tableButtons[i].addActionListener(new GuessNumberController(this));
			playingPanel.add(tableButtons[i]);
			
		} 
			
			
		}
	
		
		private void addListenerToTheControlPanelButtons() {
			
			start.addActionListener(new GuessNumberController(this));
			stop.addActionListener(new GuessNumberController(this));
			newGame.addActionListener(new GuessNumberController(this));
			save.addActionListener(new GuessNumberController(this));
			load.addActionListener(new GuessNumberController(this));
			ranking.addActionListener(new GuessNumberController(this));
			clearTable.addActionListener(new GuessNumberController(this));
			exit.addActionListener(new GuessNumberController(this));
			
			
			
		}
	
	}
	


